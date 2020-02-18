import com.snake.app.App;
import com.snake.app.User;
import com.snake.app.UserController;
import com.snake.app.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = App.class)
@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class UserControllerTest {

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserController userController;

    /**
     * Check whether the userController is loaded correctly.
     * @throws Exception exception.
     */
    @Test
    public void contexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    /**
     * Checks whether the userController correctly retrieves a list
     * of the top 5 users.
     */
    @Test
    public void findList() {
        List<User> list = new ArrayList<>();
        User user1 = new User("user1","pwd", 50);
        User user2 = new User("user2","pwd", 40);
        User user3 = new User("user3","pwd", 35);
        User user4 = new User("user4","pwd", 30);
        User user5 = new User("user5","pwd", 20);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        when(userRepository.findTop5ByOrderByHighscoreDesc()).thenReturn(list);
        List<User> what = userController.actualListTop5();
        assertThat(what.size()).isEqualTo(5);
    }

    /**
     * Checks whether the userController correctly finds the top 5
     * users as a string.
     */
    @Test
    public void findStringList() {
        List<User> list = new ArrayList<>();
        User user1 = new User("user1","pwd", 50);
        User user2 = new User("user2","pwd", 40);
        User user3 = new User("user3","pwd", 35);
        User user4 = new User("user4","pwd", 30);
        User user5 = new User("user5","pwd", 20);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        when(userRepository.findTop5ByOrderByHighscoreDesc()).thenReturn(list);
        String what = userController.listTop5();
        System.out.println("what " + what);
        assertThat(what.contains("user1")).isTrue();
    }

    /**
     * Checks whether the userController correctly retrieves an existing user's
     * highscore.
     */
    @Test
    public void userHighscoreNotNullTest() {
        User user5 = new User("user5","pwd", 20);

        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Double highscore = userController.userHighscore(user5);
        assertThat(highscore).isEqualTo(20);
    }

    /**
     * Checks whether the userController correctly retrieves an non-existing user's
     * highscore.
     */
    @Test
    public void userHighscoreNullTest() {
        User user5 = new User("user5","pwd", 20);

        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Double highscore = userController.userHighscore(user5);
        assertThat(highscore).isEqualTo(null);
    }

    /**
     * Checks whether the userController correctly logs an user in.
     */
    @Test
    public void loginNotNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(passwordEncoder.matches("pwd", "pwd")).thenReturn(true);
        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Boolean login = userController.login(user5);
        assertThat(login).isEqualTo(true);
    }

    /**
     * Checks whether the userController correctly not logs an user in.
     */
    @Test
    public void loginNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(passwordEncoder.matches("pwd", "pwd")).thenReturn(true);
        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Boolean login = userController.login(user5);
        assertThat(login).isEqualTo(false);
    }

    /**
     * Checks whether the userController correctly retrieves the highscore
     * updatable boolean of an existing user.
     */
    @Test
    public void getHighscoreNotNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Boolean getHighscore = userController.getHighscore(user5);
        assertThat(getHighscore).isEqualTo(false);
    }

    /**
     * Checks whether the userController correctly retrieves the highscore
     * updatable boolean of an non-existing user.
     */
    @Test
    public void getHighscoreNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Boolean getHighscore = userController.getHighscore(user5);
        assertThat(getHighscore).isEqualTo(false);
    }

    /**
     * Checks whether the userController correctly sets the highscore
     * updatable boolean of an existing user.
     */
    @Test
    public void setHighscoreNotNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Boolean setHighscore = userController.setHighscore(user5);
        assertThat(setHighscore).isEqualTo(true);
        assertThat(user5.getHighscore()).isEqualTo(20);
    }

    /**
     * Checks whether the userController correctly sets the highscore
     * of an non-existing user.
     */
    @Test
    public void setHighscoreNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Boolean setHighscore = userController.setHighscore(user5);
        assertThat(setHighscore).isEqualTo(false);
    }

    /**
     * Checks whether the userController correctly denies sign up for
     * an existing username.
     */
    @Test
    public void signupNotNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Boolean signup = userController.signup(user5);
        assertThat(signup).isEqualTo(false);
    }

    /**
     * Checks whether the userController correctly signs a user up for a free
     * username.
     */
    @Test
    public void signupNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Boolean signup = userController.signup(user5);
        assertThat(signup).isEqualTo(true);
    }

    /**
     * Test the truncating function of the userController.
     */
    @Test
    public void truncateTest() {
        String result = userController.clear();
        assertThat(result).isEqualTo("Database truncated");
    }

    /**
     * Checks whether the userController correctly retrieves all the current users
     * in the database.
     */
    @Test
    public void findAllTest() {
        List<User> list = new ArrayList<>();
        User user1 = new User("user1","pwd", 50);
        User user2 = new User("user2","pwd", 40);
        User user3 = new User("user3","pwd", 35);
        User user4 = new User("user4","pwd", 30);
        User user5 = new User("user5","pwd", 20);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        when(userRepository.findAll()).thenReturn(list);
        List<User> what = userController.hello();
        assertThat(what.size()).isEqualTo(5);
        assertThat(what.get(0)).isEqualTo(user1);
        assertThat(what.get(1)).isEqualTo(user2);
        assertThat(what.get(2)).isEqualTo(user3);
        assertThat(what.get(3)).isEqualTo(user4);
        assertThat(what.get(4)).isEqualTo(user5);
    }

    @Test
    public void deleteNotNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(user5);
        Boolean delete = userController.deleteUser(user5);
        assertThat(delete).isEqualTo(true);
    }

    @Test
    public void deleteNullTest() {
        User user5 = new User("user5","pwd", 20);
        when(userRepository.findUserByUserName("user5")).thenReturn(null);
        Boolean delete = userController.deleteUser(user5);
        assertThat(delete).isEqualTo(false);
    }

}
