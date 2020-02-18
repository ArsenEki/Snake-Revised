package board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize"})
public class LocationUnitTest {

    Location loc1;
    Location loc2;
    Location loc3;
    int x1 = 10;
    int y1 = 20;

    /**
     * Before each method for this class.
     */
    @BeforeEach
    public void setUp() {
        loc1 = new Location(x1, y1);
        loc2 = new Location(x1, y1, Direction.RIGHT);
        loc3 = new Location(x1, y1, -90);
    }

    @Test
    public void constructorTest() {
        assertThat(loc1).isEqualTo(new Location(10,20));
    }

    @Test
    public void constructor2Test() {
        assertThat(loc2).isEqualTo(loc3);
    }

    @Test
    public void equalsTest() {
        assertThat(loc1).isEqualTo(loc1);
        assertThat(loc1).isNotEqualTo(null);
    }

    @Test
    public void rotationDirectionTest() {
        loc1.setDirection(Direction.LEFT);
        assertThat(loc1.getRotation()).isEqualTo(90);
    }

    @Test
    public void getLocXTest() {
        assertThat(loc1.getLocX()).isEqualTo(x1);
    }

    @Test
    public void getLocYTest() {
        assertThat(loc1.getLocY()).isEqualTo(y1);
    }

    @Test
    public void toStringTest() {
        assertThat(loc1.toString()).isEqualTo("Location{locX=10, locY=20, direction=null}");
    }
}
