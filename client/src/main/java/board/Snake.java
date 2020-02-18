package board;

import gui.GraphicsBounds;

import java.util.LinkedList;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic"})
public class Snake {
    // Body of the snake every segment Location stored in a list/
    private LinkedList<Location> body = new LinkedList<>();

    private boolean grow = false;

    private Location snakeTail;

    private Direction direction = Direction.RIGHT;
    private Direction prevDir = direction;

    //This boolean is needed to control that there is only one update per cycle
    private boolean moveMade = true;

    private int segmentOffset = GraphicsBounds.spriteSize;


    protected final int snakeSpeed = GraphicsBounds.spriteSize;

    /**
     * Constructor for a Snake object. Will construct a snake left of the head.
     * @param snakeHead The head location of the snake.
     * @param initialLength The starting length of the snake.
     */
    public Snake(Location snakeHead, int initialLength) {
        int posX = snakeHead.locX;
        int posY = snakeHead.locY;
        for (int i = 0; i < initialLength; i++) {
            body.add(new Location(posX, posY, Direction.RIGHT));

            posX -= segmentOffset;

        }
        snakeTail = body.getLast();
    }

    /**
     * this method will make a deep copy of a snake.
     * @param snake the snake to be coppied
     */
    public Snake(Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            Location snB = snake.getBody().get(i);
            this.body.add(new Location(snB.locX, snB.locY, Direction.RIGHT));
        }

        snakeTail = body.getLast();
    }



    /**
     * Getter for the body of the snake.
     * @return snake's body.
     */
    public LinkedList<Location> getBody() {
        return this.body;
    }


    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * This method sets the new direction of the snake after some keyboard input.
     * It shouldn't be possible if it is in conflict with the current movement.
     * @param dir An enum either: "LEFT", "RIGHT", "UP" or "DOWN"
     */
    public boolean setDirection(Direction dir) {
        //If a new direction was set but the update wasn't made yet,
        // you cannot change the direction.
        if (!moveMade) {
            return false;
        }
        if (!direction.isValid(dir)) {
            return false;
        }
        prevDir = direction;
        direction = dir;
        moveMade = false;
        return true;
    }

    /**
     * If this method is called the snake will be updated and collisions should be checked.
     */
    public void move(int tick) {
        moveMade = true;
        Location newTail;
        if (grow) {

            switch (body.getLast().direction) {
                case RIGHT:
                    newTail = new Location(body.getLast().locX - GraphicsBounds.spriteSize,
                            body.getLast().locY, body.getLast().direction);
                    break;
                case LEFT:
                    newTail = new Location(body.getLast().locX + GraphicsBounds.spriteSize,
                            body.getLast().locY, body.getLast().direction);
                    break;
                case UP:
                    newTail = new Location(body.getLast().locX, body.getLast().locY
                            + GraphicsBounds.spriteSize, body.getLast().direction);
                    break;
                case DOWN:
                    newTail = new Location(body.getLast().locX, body.getLast().locY
                            - GraphicsBounds.spriteSize, body.getLast().direction);
                    break;
                default:
                    newTail = new Location(0,0);
                    break;

            }


            body.addLast(newTail);
        }

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).direction = body.get(i - 1).direction;
        }

        switch (direction) {
            case RIGHT:
                body.getFirst().direction = Direction.RIGHT;
                break;
            case LEFT:
                body.getFirst().direction = Direction.LEFT;
                break;
            case UP:
                body.getFirst().direction = Direction.UP;
                break;
            case DOWN:
                body.getFirst().direction = Direction.DOWN;
                break;
            default:
                break;
        }



        for (int i = body.size() - 1; i >= 0; i--) {
            Direction dir = body.get(i).direction;
            Location segment = body.get(i);
            switch (dir) {
                case RIGHT:
                    segment.locX += snakeSpeed;
                    break;
                case LEFT:
                    segment.locX -= snakeSpeed;
                    break;
                case UP:
                    segment.locY -= snakeSpeed;
                    break;
                case DOWN:
                    segment.locY += snakeSpeed;
                    break;
                default:
                    break;
            }
        }



        grow = false;
    }

    /**
     * If the snake has eaten an apple, this method will be called which
     * will make sure that the snake will growSnake one segment.
     */
    public void growSnake() {
        grow = true;
    }

    /**
     * This method makes a deep copy of the snake body.
     * @return deep copy.
     */
    public LinkedList<Location> cloneBody() {
        LinkedList<Location> clone = new LinkedList<>();
        for (Location loc : body) {
            clone.add(new Location(loc.locX, loc.locY, loc.direction));
        }
        return clone;
    }
}
