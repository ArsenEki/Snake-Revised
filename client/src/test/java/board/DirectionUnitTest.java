package board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DirectionUnitTest {

    private transient Direction dir;

    @BeforeEach
    public void setUp() {
        dir = Direction.RIGHT;
    }

    @Test
    // You shouldn't be able to update in the direction opposite to the one you
    // are moving in right now
    public void oppositeMoveTest() {
        dir = Direction.RIGHT;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(false);
        dir = Direction.LEFT;
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(false);
        dir = Direction.UP;
        assertThat(dir.isValid(Direction.DOWN)).isEqualTo(false);
        dir = Direction.DOWN;
        assertThat(dir.isValid(Direction.UP)).isEqualTo(false);
    }

    @Test
    public void correctMoveRightTest() {
        dir = Direction.RIGHT;
        assertThat(dir.isValid(Direction.DOWN)).isEqualTo(true);
        assertThat(dir.isValid(Direction.UP)).isEqualTo(true);
    }

    @Test
    public void correctMoveLeftTest() {
        dir = Direction.LEFT;
        assertThat(dir.isValid(Direction.DOWN)).isEqualTo(true);
        assertThat(dir.isValid(Direction.UP)).isEqualTo(true);
        dir = Direction.UP;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(true);
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(true);
        dir = Direction.DOWN;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(true);
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(true);
    }

    @Test
    public void correctMoveUpTest() {
        dir = Direction.UP;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(true);
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(true);
        dir = Direction.DOWN;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(true);
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(true);
    }

    @Test
    public void correctMoveDownTest() {
        dir = Direction.DOWN;
        assertThat(dir.isValid(Direction.LEFT)).isEqualTo(true);
        assertThat(dir.isValid(Direction.RIGHT)).isEqualTo(true);
    }

}
