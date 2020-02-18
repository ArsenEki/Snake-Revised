package com.snake.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class loads the database with some standard users,
 * such as the admin user.
 */
@Component
public class DatabaseSeeder {

    private transient UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUserTable();
    }

    transient String succes = "account has been seeded successfully.";


    //sets the database with some user(s) to start with.
    private void seedUserTable() {
        User user = userRepository.findUserByUserName("admin");

        //if no user is found with username admin, it will create one.
        if (user == null) {
            User tempUser = new User();
            tempUser.setUserName("admin");
            tempUser.setHighscore(420);
            tempUser.setPassword(new BCryptPasswordEncoder().encode("pwd"));
            userRepository.save(tempUser);
            System.out.println("Server: the default admin" + succes);
        } else {
            //if it can find an admin account it will tell you.
            System.out.println("Server: the default admin account seeding was not required.");
        }

        User user1 = userRepository.findUserByUserName("user1");
        //If test user1 can't be found, the rest isn't there either.
        if (user1 == null) {
            User tempUser1 = new User();
            tempUser1.setUserName("user1");
            tempUser1.setPassword(new BCryptPasswordEncoder().encode("pwd1"));
            tempUser1.setHighscore(10);
            userRepository.save(tempUser1);
            System.out.println("Server: the default 1st user " + succes);

            User tempUser2 = new User();
            tempUser2.setUserName("user2");
            tempUser2.setHighscore(20);
            tempUser2.setPassword(new BCryptPasswordEncoder().encode("pwd2"));
            userRepository.save(tempUser2);
            System.out.println("Server: the default 2nd user " + succes);

            User tempUser3 = new User();
            tempUser3.setUserName("user3");
            tempUser3.setPassword(new BCryptPasswordEncoder().encode("pwd3"));
            tempUser3.setHighscore(30);
            userRepository.save(tempUser3);
            System.out.println("Server: the default 3rd user " + succes);

            User tempUser4 = new User();
            tempUser4.setUserName("user4");
            tempUser4.setPassword(new BCryptPasswordEncoder().encode("pwd4"));
            tempUser4.setHighscore(40);
            userRepository.save(tempUser4);
            System.out.println("Server: the default 4th user " + succes);
        } else {
            //if it can find a user1 account it will tell you.
            System.out.println("Server: the default user "
                    + "accounts seeding was not required.");
        }
    }
}
