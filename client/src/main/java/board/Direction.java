package board;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;

    private static final Map<Direction, Integer> rotValues;
    private static final Map<Integer, Direction> dirValues;

    static {
        rotValues = new HashMap<>();
        dirValues = new HashMap<>();
        rotValues.put(RIGHT, -90);
        rotValues.put(UP, 180);
        rotValues.put(LEFT, 90);
        rotValues.put(DOWN, 0);
        dirValues.put(-90, RIGHT);
        dirValues.put(180, UP);
        dirValues.put(90, LEFT);
        dirValues.put(0, DOWN);
    }

    /**
     * We should check if the snake is allowed to move in a certain direction.
     * If it is moving right it cannot start moving left immediately.
     * @param dir Direction to check
     * @return If it is allowed to make that direction change
     */
    public boolean isValid(Direction dir) {
        if (this.equals(Direction.RIGHT) && dir.equals(Direction.LEFT)) {
            return false;
        }
        if (this.equals(Direction.LEFT) && dir.equals(Direction.RIGHT)) {
            return false;
        }
        if (this.equals(Direction.UP) && dir.equals(Direction.DOWN)) {
            return false;
        }
        if (this.equals(Direction.DOWN) && dir.equals(Direction.UP)) {
            return false;
        }
        return true;
    }

    public int rotation() {
        return rotValues.get(this);
    }

    public static Direction direction(int rot) {
        return dirValues.get(rot);
    }
}
