package gui;

import java.awt.image.BufferedImage;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.AvoidLiteralsInIfCondition"})
public class SpriteSheet {
    BufferedImage image;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    /**
     * This method will load a particular part of a sprite image.
     * @param col the column to load from.
     * @param row the row to load from.
     * @param width the width to load from.
     * @param height the height to load from.
     * @param offset the offset for counting.
     * @return the loaded image.
     */
    public BufferedImage grabImage(int col, int row, int width, int height, int offset) {
        BufferedImage img = image.getSubimage((col * offset) - offset,
                (row * offset) - offset, width, height);
        return img;
    }
}
