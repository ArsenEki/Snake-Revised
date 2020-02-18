package board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize"})
public class SnakeUnitTest {

    LinkedList<Location> prevBody;
    Location prevHead;
    Snake snake;

    /**
     * Setup method for the SnakeUnitTest.
     */
    @BeforeEach
    public void setUp() {
        prevHead = new Location(5,5, Direction.RIGHT);
        snake = new Snake(prevHead, 5);
        prevBody = snake.cloneBody();
    }

    @Test
    // The snake's head should start moving right
    public void rightMoveTest() {
        snake.setDirection(Direction.RIGHT);
        snake.move(14);
        assertThat(snake.getBody().peekFirst()).isEqualTo(
                new Location(prevHead.locX + snake.snakeSpeed, prevHead.locY, Direction.RIGHT));
    }

    @Test
    // The snake is facing right so it shouldn't update left but stay moving right
    public void leftNoMoveTest() {
        // Starting position should already be right, but to be save
        snake.setDirection(Direction.RIGHT);
        // Try setting direction left while the snake is headed right
        // and therefore can't do anything
        snake.setDirection(Direction.LEFT);
        snake.move(14);
        assertThat(snake.getBody().peekFirst()).isEqualTo(
                new Location(prevHead.locX + snake.snakeSpeed, prevHead.locY, Direction.RIGHT));
    }

    @Test
    public void upMoveTest() {
        snake.setDirection(Direction.UP);
        snake.move(14);
        assertThat(snake.getBody().peekFirst()).isEqualTo(
                new Location(prevHead.locX, prevHead.locY - snake.snakeSpeed, Direction.UP));
    }

    @Test
    public void downMoveTest() {
        snake.setDirection(Direction.DOWN);
        snake.move(14);
        assertThat(snake.getBody().peekFirst()).isEqualTo(
                new Location(prevHead.locX, prevHead.locY + snake.snakeSpeed, Direction.DOWN));
    }

    @Test
    // board.Snake moving straight should update in a straight line
    public void straightUpdateTest() {
        snake.setDirection(Direction.RIGHT);
        snake.move(14);
        for (int i = 0; i < prevBody.size(); i++) {
            prevBody.get(i).locX += snake.snakeSpeed;
        }
        assertThat(snake.getBody()).isEqualTo(prevBody);
    }

    @Test
    // board.Snake is now creating a new corner should update accordingly
    public void angleMoveUpdateTest() {
        snake.setDirection(Direction.UP);
        prevHead = prevBody.peekFirst();
        snake.move(14);
        prevBody.get(0).locY -= snake.snakeSpeed;
        prevBody.get(0).setDirection(Direction.UP);
        for (int i = 1; i < prevBody.size(); i++) {
            prevBody.get(i).locX += snake.snakeSpeed;
        }
        assertThat(snake.getBody()).isEqualTo(prevBody);
    }

    @Test
    // Spam test, only pick the first input
    public void spamMoveTest() {
        snake.setDirection(Direction.UP);
        snake.setDirection(Direction.LEFT);
        snake.setDirection(Direction.RIGHT);
        snake.move(14);
        prevBody.get(0).locY -= snake.snakeSpeed;
        prevBody.get(0).setDirection(Direction.UP);
        for (int i = 1; i < prevBody.size(); i++) {
            prevBody.get(i).locX += snake.snakeSpeed;
        }
        assertThat(snake.getBody()).isEqualTo(prevBody);
    }
}
