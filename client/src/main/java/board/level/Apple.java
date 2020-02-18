package board.level;

import board.Location;
import gui.AudioHandling;
import gui.MainGameWindow;

import java.awt.Color;

public class Apple extends Edible {

    private transient int scoreInc = 1;

    public Apple(Location loc, Level level) {
        super(loc, level);
        spriteColor = Color.RED;
    }

    /**
     * Called if a collision with an Apple happens.
     */
    public void collided() {
        level.getSnake().growSnake();
        level.increaseScore(scoreInc);
        AudioHandling.playEatSound();
    }
}
