package authentication;

import static org.assertj.core.api.Assertions.assertThat;

import authentication.dialogs.User;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class for the User object used in server communication.
 */
@SuppressWarnings({"PMD.AvoidDuplicateLiterals"})
public class UserTest {

    transient User testUser;
    transient User testUser2;

    @BeforeEach
    public void setUp() {
        testUser = new User("test", "test2", 10.0);
        testUser2 = new User("test", "test2", 10.0);
    }

    /**
     * Tests the constructor using 3 fields.
     */
    @Test
    public void constructorAllTest() {
        assertThat(testUser.getUserName()).isEqualTo("test");
        assertThat(testUser.getPassword()).isEqualTo("test2");
        assertThat(testUser.getHighscore()).isEqualTo(10.0);
    }

    /**
     * Tests the constructor using 3 fields.
     */
    @Test
    public void constructorTwoTest() {
        testUser = new User("testt", "testt2");
        assertThat(testUser.getUserName()).isEqualTo("testt");
        assertThat(testUser.getPassword()).isEqualTo("testt2");
        assertThat(testUser.getHighscore()).isEqualTo(0.0);
    }

    /**
     * Tests the empty constructor, setting none of the fields.
     */
    @Test
    public void constructorNoneTest() {
        testUser = new User();
        assertThat(testUser.getUserName()).isNull();
        assertThat(testUser.getPassword()).isNull();
        assertThat(testUser.getHighscore()).isEqualTo(0);
    }

    /**
     * Tests the User class's setters.
     */
    @Test
    public void setterTest() {
        testUser.setUserName("test2");
        testUser.setPassword("test3");
        testUser.setHighscore(20.0);
        assertThat(testUser.getUserName()).isEqualTo("test2");
        assertThat(testUser.getPassword()).isEqualTo("test3");
        assertThat(testUser.getHighscore()).isEqualTo(20.0);
    }

    /**
     * Unique usernames must hold.
     */
    @Test
    public void notEqualsTest() {
        testUser2 = new User("test2", "test2", 10.0);
        assertThat(testUser).isNotEqualTo(testUser2);
        assertThat(testUser).isNotEqualTo(null);
    }

    /**
     * Same username or same object equals true.
     */
    @Test
    public void equalsTest() {
        //equals same name and password.
        assertThat(testUser).isEqualTo(testUser2);
        //equals itself.
        assertThat(testUser).isEqualTo(testUser);
    }

    /**
     * Hash test.
     */
    @Test
    public void hashTest() {
        assertThat(testUser.hashCode()).isEqualTo(Objects.hash(testUser.getUserName(),
                testUser.getPassword(), testUser.getHighscore()));
    }

    /**
     * Checks toString method.
     */
    @Test
    public void toStringTest() {
        assertThat(testUser.toString()).isEqualTo("User: test, password: test2, highscore: 10.0");
    }

}
