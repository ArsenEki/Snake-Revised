package com.snake.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.DataflowAnomalyAnalysis",
        "PMD.BeanMembersShouldSerialize"})
@RestController
public class UserController {

    /**
     * Creates a userRepo to communicate with the DB.
     */
    @Autowired
    transient UserRepository userRepository;

    /**
     * Creates a passwordEncoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Find all users.
     * @return message.
     */
    @GetMapping("/hello")
    public List<User> hello() {
        return userRepository.findAll();
    }

    /**
     * Truncate database at get.
     * @return message.
     */
    @GetMapping("/clear")
    public String clear() {
        userRepository.truncate();
        return "Database truncated";
    }

    /**
     * Signs a user into the database.
     * @param user user to be registered.
     * @return whether signup was successful.
     */
    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    public Boolean signup(@RequestBody User user) {
        String hashedPassword = passwordEncoder.encode(
                user.getPassword());
        User db = userRepository.findUserByUserName(user.getUserName());
        if (db == null) {
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the users highscore.
     * @param user user to be updated.
     * @return whether update was successful.
     */
    @PostMapping(path = "/sethighscore", consumes = "application/json",
            produces = "application/json")
    public Boolean setHighscore(@RequestBody User user) {
        User db = userRepository.findUserByUserName(user.getUserName());
        if (db == null) {
            return false;
        } else {
            db.setHighscore(user.getHighscore());
            userRepository.save(db);
            System.out.println("Highscore for user " + db.getUserName()
                    + " is set to " + db.getHighscore());
            return true;
        }
    }

    /**
     * Retrieves whether the user's highscore should be updated or not.
     * @param user to check.
     * @return boolean to update highscore or not.
     */
    @PostMapping(path = "/gethighscore", consumes = "application/json",
            produces = "application/json")
    public Boolean getHighscore(@RequestBody User user) {
        User db = userRepository.findUserByUserName(user.getUserName());
        if (db == null) {
            return false;
        }
        return (user.getHighscore() > db.getHighscore());
    }

    /**
     * Retrieves double of users highscore.
     * @param user to be checked.
     * @return double.
     */
    @PostMapping(path = "/user/highscore", consumes = "application/json",
            produces = "application/json")
    public Double userHighscore(@RequestBody User user) {
        User db = userRepository.findUserByUserName(user.getUserName());
        if (db == null) {
            return null;
        }
        return (db.getHighscore());
    }

    /**
     * Authenticate a user.
     * @param user user.
     * @return true or false.
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public boolean login(@RequestBody User user) {
        User db = userRepository.findUserByUserName(user.getUserName());
        if (db == null) {
            return false;
        }
        return ((passwordEncoder.matches(user.getPassword(), db.getPassword())));
    }


    /**
     * Highscores from database.
     * @return a list of top 5 users.
     */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public @ResponseBody String listTop5() {
        List<User> list = userRepository.findTop5ByOrderByHighscoreDesc();
        System.out.println("Highscores sent: " + list.toString());
        return list.toString();
    }

    /**
     * Highscores from database.
     * @return a list of top 5 users.
     */
    @RequestMapping(value = "/user/all/list", method = RequestMethod.GET)
    public @ResponseBody List<User> actualListTop5() {
        return userRepository.findTop5ByOrderByHighscoreDesc();
    }

    /**
     * Authenticate a user.
     * @param user user.
     * @return true or false.
     */
    @PostMapping(path = "/delete", consumes = "application/json", produces = "application/json")
    public boolean deleteUser(@RequestBody User user) {
        User db = userRepository.findUserByUserName(user.getUserName());

        if (db == null) {
            return false;
        }
        System.out.println("Deleting user: " + db.getUserName());
        userRepository.deleteByUserName(db.getUserName());
        return true;
    }


}

