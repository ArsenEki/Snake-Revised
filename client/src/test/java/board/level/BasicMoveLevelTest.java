package board.level;

import static org.assertj.core.api.Assertions.assertThat;

import board.Direction;
import board.Location;
import board.Snake;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis"})
class BasicMoveLevelTest {

    Level basicLevel;
    LinkedList<Location> prevBody;
    Location prevHead;
    Snake snake;

    @BeforeEach
    public void setUp() {
        basicLevel = LevelParser.parseEmpty();
        snake = basicLevel.getSnake();
        prevBody = snake.cloneBody();
    }

    @Test
    public void constructorTest() {
        assertThat(basicLevel.getScore()).isEqualTo(0);
        int[] dimensions = {basicLevel.getBoardX(), basicLevel.getBoardX()};
        assertThat(basicLevel.dimensions).isEqualTo(dimensions);
        assertThat(prevBody.peek().locX).isEqualTo(basicLevel.getBoardX() / 2);
        assertThat(prevBody.peek().locY).isEqualTo(basicLevel.getBoardX() / 2);
    }

    @Test
    // Tests if the snake grows the step after it ate the apple.
    public void appleCollisionTest() {
        int prevHeadX = prevBody.peekFirst().locX;
        int prevHeadY = prevBody.peekFirst().locY;
        basicLevel.edible = new Apple(new Location(prevHeadX + snake.getSnakeSpeed(),
                prevHeadY), basicLevel);
        snake.setDirection(Direction.RIGHT);
        final int prevLength = prevBody.size();
        basicLevel.update(14);
        basicLevel.update(14);
        for (int i = 0; i < prevBody.size(); i++) {
            prevBody.get(i).locX += 2 * snake.getSnakeSpeed();
        }
        prevBody.addLast(new Location(prevBody.getLast().locX - snake.getSnakeSpeed(),
                prevBody.getLast().locY, Direction.RIGHT));
        assertThat(prevLength + 1).isEqualTo(basicLevel.getSnake().getBody().size());
        assertThat(snake.getBody()).isEqualTo(prevBody);
    }

    @Test
    // Tests if the snake grows the step after it ate the apple.
    public void bananaCollisionTest() {
        int prevHeadX = prevBody.peekFirst().locX;
        int prevHeadY = prevBody.peekFirst().locY;
        basicLevel.edible = new Banana(new Location(prevHeadX + snake.getSnakeSpeed(),
                prevHeadY), basicLevel);
        snake.setDirection(Direction.RIGHT);
        final int prevLength = prevBody.size();
        basicLevel.update(14);
        basicLevel.update(14);
        for (int i = 0; i < prevBody.size(); i++) {
            prevBody.get(i).locX += 2 * snake.getSnakeSpeed();
        }
        prevBody.addLast(new Location(prevBody.getLast().locX - snake.getSnakeSpeed(),
                prevBody.getLast().locY, Direction.RIGHT));
        assertThat(prevLength + 1).isEqualTo(basicLevel.getSnake().getBody().size());
        assertThat(snake.getBody()).isEqualTo(prevBody);
    }

    @Test
    // Test if the apple is located on the board.
    public void assignAppleTest() {
        basicLevel.randomEdible();
        assertThat(basicLevel.getEdibleLoc().locX).isBetween(0, basicLevel.getBoardX() - 1);
        assertThat(basicLevel.getEdibleLoc().locY).isBetween(0, basicLevel.getBoardX() - 1);
    }

    @Test
    // Test if the apple is located on the board.
    public void assignBananaTest() {
        Edible ed = basicLevel.getEdible();
        Banana.delay = 1;
        Banana b = new Banana(ed.getLocation(), basicLevel);
        basicLevel.edible = b;
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(basicLevel.getEdibleLoc().locX).isBetween(0, basicLevel.getBoardX() - 1);
        assertThat(basicLevel.getEdibleLoc().locY).isBetween(0, basicLevel.getBoardX() - 1);
    }

    @Test
    // Test if the apple cannot be located behind the snake.
    public void appleBehindSnakeTest() {
        basicLevel = LevelParser.parseFile("../../test/resources/4x4.txt");
        snake = basicLevel.getSnake();

        int center = basicLevel.getBoardX() / (2 * basicLevel.countSquares);
        snake.getBody().clear();
        snake.getBody().add(new Location(3 * center, 3 * center, Direction.RIGHT));
        snake.getBody().add(new Location(3 * center, 5 * center, Direction.RIGHT));
        snake.getBody().add(new Location(5 * center, 3 * center, Direction.RIGHT));

        basicLevel.randomEdible();
        assertThat(basicLevel.getEdibleLoc()).isEqualTo(new Location(
                5 * center, 5 * center));
    }

    @Test
    // Check if collision with the right wall is functioning.
    public void rightWallCollisionTest() {
        snake.setDirection(Direction.RIGHT);
        assertThat(basicLevel.isInProgress()).isTrue();
        while (snake.getBody().peekFirst().locX < basicLevel.getBoardX()) {
            basicLevel.update(14);
        }
        assertThat(basicLevel.isInProgress()).isFalse();
    }

    @Test
    // Check if collision with the left wall is functioning.
    public void leftWallCollisionTest() {
        snake.setDirection(Direction.UP);
        basicLevel.update(14);
        snake.setDirection(Direction.LEFT);
        assertThat(basicLevel.isInProgress()).isTrue();
        while (snake.getBody().peekFirst().locX >= 0) {
            basicLevel.update(14);
        }
        assertThat(basicLevel.isInProgress()).isFalse();
    }

    @Test
    // Check if collision with the upper wall is functioning.
    public void upperWallCollisionTest() {
        snake.setDirection(Direction.UP);
        assertThat(basicLevel.isInProgress()).isTrue();
        while (snake.getBody().peekFirst().locY >= 0) {
            basicLevel.update(14);
        }
        assertThat(basicLevel.isInProgress()).isFalse();
    }

    @Test
    // Check if collision with the lower wall is functioning.
    public void lowerWallCollisionTest() {
        snake.setDirection(Direction.DOWN);
        assertThat(basicLevel.isInProgress()).isTrue();
        while (snake.getBody().peekFirst().locY < basicLevel.getBoardX()) {
            basicLevel.update(14);
        }
        assertThat(basicLevel.isInProgress()).isFalse();
    }

    @Test
    // Check if collision with itself stop the game.
    public void selfCollision() {
        basicLevel = LevelParser.parseFile("../../test/resources/selfCollision.txt");
        assertThat(basicLevel.isInProgress()).isTrue();
        basicLevel.getSnake().setDirection(Direction.UP);
        basicLevel.update(14);
        basicLevel.getSnake().setDirection(Direction.LEFT);
        basicLevel.update(14);
        basicLevel.getSnake().setDirection(Direction.DOWN);
        basicLevel.update(14);
        assertThat(basicLevel.isInProgress()).isFalse();
    }
}