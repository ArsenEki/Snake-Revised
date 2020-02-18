import com.snake.app.App;
import com.snake.app.DatabaseSeeder;
import com.snake.app.User;
import com.snake.app.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Ignore("JUnit 4 is used, not 5 so ignore.")
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;

    private DatabaseSeeder seeder;

    private ContextRefreshedEvent event;

    @Autowired
    private ApplicationContext appContext;

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    @BeforeEach
    public void save() {
        userRepository.truncate();
        user1 = new User("user1", "pwd", 10);
        user2 = new User("user2", "pwd", 20);
        user3 = new User("user3", "pwd", 30);
        user4 = new User("user4", "pwd", 40);
        user5 = new User("user5", "pwd", 50);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }

    /**
     * This tests whether the userRepository is able to correctly
     * find a user by their username.
     */
    @Test
    public void find() {
        System.out.println("Here");
        assertEquals(userRepository.findUserByUserName("user1"), user1);
        assertEquals(userRepository.findUserByUserName("user2"), user2);
        assertEquals(userRepository.findUserByUserName("user3"), user3);
        assertEquals(userRepository.findUserByUserName("user4"), user4);
        assertEquals(userRepository.findUserByUserName("user5"), user5);
    }

    /**
     * This test tests the retrieval of the top 5 players sorted
     * by their highscore descending.
     */
    @Test
    public void getTop5Test() {
        List<User> result = new ArrayList<>();
        result.add(user5);
        result.add(user4);
        result.add(user3);
        result.add(user2);
        result.add(user1);
        assertEquals(result, userRepository.findTop5ByOrderByHighscoreDesc());
        assertEquals(50, userRepository.findTop5ByOrderByHighscoreDesc()
                .get(0).getHighscore());
    }

    /**
     * Test that when we run the seeder again, not another admin account
     * will be made. If it does this test would fail to a non-unique result
     * constraint being violated by the .findByUsername method.
     */
    @Test
    public void reseedTest() {
        event = new ContextRefreshedEvent(appContext);
        seeder = new DatabaseSeeder(userRepository);
        userRepository.truncate();
        seeder.seed(event);
        assertThat(userRepository.findUserByUserName("admin")).isNotNull();
        seeder.seed(event);
        assertThat(userRepository.findUserByUserName("admin")).isNotNull();
    }

}
