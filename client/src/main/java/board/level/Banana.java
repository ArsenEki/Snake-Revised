package board.level;

import board.Location;
import gui.AudioHandling;
import gui.MainGameWindow;
import gui.StartScreenNew;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("PMD.AssignmentToNonFinalStatic")
public class Banana extends Edible {

    protected static transient int delay = 6000;

    transient int scoreInc = 2;
    transient boolean existing = true;

    /**
     * Constructor of the banana.
     * @param loc Location.
     * @param level Current level.
     */
    public Banana(Location loc, Level level) {
        super(loc, level);
        spriteColor = Color.YELLOW;
        Location head = level.getSnake().getBody().getFirst();
        int distance = Math.abs(head.locX - loc.locX) + Math.abs(head.locY - loc.locY);
        delay = Math.min(distance * 10 + 3000, delay);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        scoreInc = 0;
                        spriteColor = new Color(147, 91, 62);
                        try {
                            if (StartScreenNew.classic) {
                                sprite = ImageIO.read(new File(
                                        "src/main/resources/edibles/BananaBrown-Classic.png"));

                            } else {
                                sprite = ImageIO.read(new File(
                                        "src/main/resources/edibles/BananaBrown.png"));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                delay
        );
    }

    /**
     * Called if a collision with an Apple happens.
     */
    public void collided() {
        existing = false;
        level.getSnake().growSnake();
        level.increaseScore(scoreInc);
        AudioHandling.playEatSound();
    }
}
