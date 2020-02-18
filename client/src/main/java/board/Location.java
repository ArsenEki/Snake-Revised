package board;

/**
 * This class initiates objects that represent a location on the board.
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.OverrideBothEqualsAndHashcode"})
public class Location {
    public int locX;
    public int locY;
    public Direction direction;

    /**
     * The constructor of locations.
     * @param locX locX-coordinate.
     * @param locY locY-coordinate.
     */
    public Location(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    /**
     * The constructor of locations.
     * @param locX locX-coordinate.
     * @param locY locY-coordinate.
     */
    public Location(int locX, int locY, int rotation) {
        this.locX = locX;
        this.locY = locY;
        this.direction = Direction.direction(rotation);
    }

    /**
     * The constructor of locations.
     * @param locX locX-coordinate.
     * @param locY locY-coordinate.
     */
    public Location(int locX, int locY, Direction dir) {
        this.locX = locX;
        this.locY = locY;
        this.direction = dir;
    }

    /**
     * Getter for the locX-coordinate.
     * @return locX.
     */
    public int getLocX() {
        return this.locX;
    }

    /**
     * Getter for the locY-coordinate.
     * @return locY.
     */
    public int getLocY() {
        return this.locY;
    }

    /**
     * Getter for the roation of the body part.
     * @return rotation
     */
    public int getRotation() {
        return this.direction.rotation();
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return locX == location.locX
                && locY == location.locY
                && direction == location.direction;
    }

    @Override
    public String toString() {
        return "Location{"
                + "locX=" + locX
                + ", locY=" + locY
                + ", direction=" + direction
                + '}';
    }
}
