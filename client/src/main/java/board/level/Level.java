package board.level;

import authentication.dialogs.User;
import authentication.dialogs.UserService;
import board.Location;
import board.Snake;
import gui.AudioHandling;
import gui.GraphicsBounds;
import gui.MainGameWindow;
import gui.Scores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.AvoidCatchingNPE", "PMD.AvoidLiteralsInIfCondition"})
public class Level {

    //The size of the board
    protected int[] dimensions;
    protected int countSquares;
    private int score;

    /**
     * Board objects: snake, obstacles and apple.
     */
    protected Snake snake;
    private Snake initSnake;
    private ArrayList<Location> obstacles;
    protected Edible edible;

    // Initially set to true, when the snake dies it will be false.
    private boolean inProgress;
    private LevelStatus gameStatus;


    /**
     * Constructor of a Level object.
     * @param dimensions the x and y size of the board in array form.
     * @param snake the snake that will be displayed on this level.
     * @param obstacles the list of obstacle locations on this level.
     */
    public Level(int[] dimensions, Snake snake, ArrayList<Location> obstacles) {
        this.dimensions = dimensions;
        this.obstacles = obstacles;
        this.snake = snake;
        this.initSnake = new Snake(snake);

        this.gameStatus = LevelStatus.INITIALISED;

        // This should be changed to only happen when the player hits start.
        inProgress = true;
        score = 0;
        Location appleLoc = snake.getBody().peek();
        appleLoc = new Location(appleLoc.getLocX() + 50, appleLoc.getLocY());
        edible = new Apple(appleLoc, this);
    }

    public Edible getEdible() {
        return this.edible;
    }

    /**
     * This method will initalise the snake and apple.
     */
    public void initialise() {
        this.score = 0;
        this.snake = new Snake(this.initSnake);
        randomEdible();
    }

    public int getBoardX() {
        return this.dimensions[0];
    }

    public LevelStatus getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(LevelStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public ArrayList<Location> getObstacles() {
        return obstacles;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean pr) {
        this.inProgress = pr;
    }

    public void increaseScore(int inc) {
        this.score = score + inc;
    }

    public Location getEdibleLoc() {
        return edible.getLocation();
    }

    /**
     * If this method is called the snake will be updated and collisions should be checked.
     */
    public void update(int tick) {
        snake.move(tick);
        edibleCollision();
        checkSelfCollision();
        checkBoardCollision();
    }

    /**
     * If there is a collision with an apple length will be incremented.
     */
    public void edibleCollision() {
        if (checkLocationCollision(edible.getLocation(), snake.getBody().peekFirst())) {
            edible.collided();
            randomEdible();
        }
    }

    /**
     * This method calculates the new location of the apple.
     */
    public final void randomEdible() {
        int center = dimensions[0] / (2 * countSquares);
        int edibleX = (int) Math.round(Math.random() * (countSquares - 2 - 1) + 1) * 2 * center
                + center;
        int edibleY = (int) Math.round(Math.random() * (countSquares - 2 - 1) + 1) * 2 * center
                + center;
        Location loc = new Location(edibleX, edibleY);

        // Check if the apple isn't placed on a place where the snake is.
        for (Location segment : snake.getBody()) {
            if (checkLocationCollision(loc, segment)) {
                randomEdible();
                return;
            }
        }

        // Check if the apple is located behind an obstacle
        for (Location obstacle : obstacles) {
            if (checkLocationCollision(loc, obstacle)) {
                randomEdible();
                return;
            }
        }
        double random = Math.random();

        if (random <= 0.8) {
            edible = new Apple(loc, this);
        } else {
            edible = new Banana(loc, this);
        }
    }

    /**
     * This method checks if the snake collided with itself or with a wall.
     */
    public void checkSelfCollision() {
        Iterator<Location> it = snake.getBody().listIterator(1);
        // Check if the head collides with another part of the body
        while (it.hasNext()) {
            Location curSegment = it.next();
            int dis = (int)Math.sqrt(Math.pow(curSegment.locX - snake.getBody().getFirst().locX, 2)
                    + Math.pow(curSegment.locY - snake.getBody().getFirst().locY, 2));
            if (dis < GraphicsBounds.spriteSize - 2) {
                inProgress = false;
                AudioHandling.playFailSound();
                setHighscore();
                return;
            }
        }
    }

    /**
     * Sets the users highscore upon death.
     */
    public void setHighscore() {
        Scores.setHighscoreUser(score, new UserService());
    }

    /**
     * Checks if there is a collision with the edges or obstacles.
     */
    public void checkBoardCollision() {
        Location snakeHead = snake.getBody().peekFirst();
        if (snakeHead.locX >= dimensions[0] || snakeHead.locX < GraphicsBounds.spriteSize
                || snakeHead.locY >= dimensions[1] || snakeHead.locY < GraphicsBounds.spriteSize) {
            inProgress = false;
            AudioHandling.playFailSound();
            setHighscore();
            return;
        }
        // Check for collision of the head with an obstacle
        for (Location obstacle : obstacles) {
            if (checkLocationCollision(obstacle, snakeHead)) {
                inProgress = false;
                AudioHandling.playFailSound();
                setHighscore();
                return;
            }
        }
    }

    /**
     * This method checks if a collidee will collide with the collider.
     * I.e. if the snake (collider) collides with the wall (collidee).
     * @param collidee
     * @param collider
     * @return
     */
    public boolean checkLocationCollision(Location collidee, Location collider) {
        if (collidee.locX + GraphicsBounds.spriteSize >= collider.locX
                && collidee.locX - GraphicsBounds.spriteSize <= collider.locX
                && collidee.locY + GraphicsBounds.spriteSize >= collider.locY
                && collidee.locY - GraphicsBounds.spriteSize <= collider.locY) {
            return true;
        }
        return false;
    }
}
