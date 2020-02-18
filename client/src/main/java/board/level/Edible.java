package board.level;

import board.Location;
import gui.StartScreenNew;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Edible {

    protected transient Location location;
    protected transient Level level;
    protected transient Color spriteColor;
    protected transient BufferedImage sprite;

    /**
     * Super constructor.
     * @param loc Location.
     * @param level Current level.
     */
    public Edible(Location loc, Level level) {
        this.location = loc;
        this.level = level;

        try {
            if (StartScreenNew.classic) {
                sprite = ImageIO.read(new File("src/main/resources/edibles/"
                        + this.getClass().getSimpleName() + "-Classic" + ".png"));
                //System.out.println("---CLASSIC EDIBLE---");
            } else {

                sprite = ImageIO.read(new File("src/main/resources/edibles/"
                        + this.getClass().getSimpleName() + ".png"));
                //System.out.println("---SMOOTH EDIBLE---");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public Location getLocation() {
        return this.location;
    }

    public abstract void collided();

    public Color edibleColor() {
        return this.spriteColor;
    }
}
