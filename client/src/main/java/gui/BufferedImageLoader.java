package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.AvoidLiteralsInIfCondition"})
public class BufferedImageLoader {
    BufferedImage image;

    /**
     * This method will load an image from specified path.
     * @param path the path from which to load the image.
     * @return the loaded image.
     * @throws IOException if something goes wrong with the image loading.
     */
    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(new File(path));
        return image;

    }
}
