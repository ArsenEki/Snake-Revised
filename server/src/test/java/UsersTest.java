import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.snake.app.User;
import com.snake.app.UserRepository;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class UsersTest {

    transient String aap = "aap";
    transient User user;
    transient User user2;
    transient String admin;
    @Autowired
    transient UserRepository userRepository;

    /**
     * Setup method.
     */
    @BeforeEach
    public void setUp() {
        user = new User();
        admin = "admin";
        user.setUserName(admin);
        user.setPassword("pwd");
        user.setHighscore(420);

        user2 = new User();
        user2.setUserName(admin);
    }

    @Test
    public void gettersTest() {
        assertThat(user.getHighscore()).isEqualTo(420);
        assertThat(user.getPassword()).isEqualTo("pwd");
        assertThat(user.getUserName()).isEqualTo(admin);
        assertThat(user.getId()).isNull();
    }

    @Test
    public void equalsTest() {
        assertEquals(user, user);
        assertEquals(user, user2);
    }

    @Test
    public void notEqualsTest() {
        User false1 = new User("admin1", "pwd", 420.0);
        assertNotEquals(user, false1);
        assertNotEquals(user, false);
        assertNotEquals(user, null);
    }

    @Test
    public void secondConstructorTest() {
        User temp = new User(aap, aap);
        assertThat(temp.getPassword()).isEqualTo(aap);
        assertThat(temp.getUserName()).isEqualTo(aap);
    }

    @Test
    public void hashTest() {
        assertEquals(user.hashCode(), Objects.hash(null, user.getUserName()));
    }

    @Test
    public void toStringTest() {
        assertEquals(user.toString(), "admin 420.0");
    }
}
