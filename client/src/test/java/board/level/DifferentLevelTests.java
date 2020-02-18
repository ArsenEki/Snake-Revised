package board.level;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import board.Direction;
import board.Location;
import board.Snake;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.BeanMembersShouldSerialize",
        "PMD.DataflowAnomalyAnalysis"})
public class DifferentLevelTests {
    Level basicLevel;
    LinkedList<Location> prevBody;
    Snake snake;

    /**
     * Setup method.
     */
    @BeforeEach
    public void setUp() {
        basicLevel = LevelParser.parseFile("../../test/resources/obstacleCollision.txt");
        prevBody = (LinkedList<Location>) basicLevel.getSnake().getBody().clone();
        snake = basicLevel.getSnake();
    }

    @Test
    public void wrongFileTest() {
        basicLevel = LevelParser.parseFile("wrongName");
        assertThat(basicLevel.getObstacles().size()).isEqualTo(0);
    }

    @Test
    public void obstacleCollisionTest() {
        assertThat(basicLevel.isInProgress()).isTrue();
        basicLevel.getSnake().setDirection(Direction.RIGHT);
        basicLevel.update(14);
        assertThat(basicLevel.isInProgress()).isFalse();
    }

    @Test
    public void appleBehindObstacleTest() {
        basicLevel = LevelParser.parseFile("../../test/resources/appleBehindObstacle.txt");
        int center = basicLevel.getBoardX() / (2 * basicLevel.countSquares);
        basicLevel.randomEdible();
        assertThat(basicLevel.getEdibleLoc()).isEqualTo(new Location(
                3 * center, 3 * center));
    }

    @Test
    public void differentLinesTest() {
        basicLevel = LevelParser.parseFile("../../test/resources/differentLines.txt");
        assertThat(basicLevel.getObstacles().size()).isEqualTo(0);
    }

    @Test
    public void differentNoNameTest() {
        basicLevel = LevelParser.parseFile("");
        assertThat(basicLevel.getObstacles().size()).isEqualTo(0);
    }
}
