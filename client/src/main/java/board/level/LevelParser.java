package board.level;

import board.Direction;
import board.Location;
import board.Snake;
import gui.BoardCanvas;
import gui.BoardPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class LevelParser {

    /**
     * Used for parsing without an InputStream.
     */
    public static Level parseEmpty() {
        BoardCanvas bc = new BoardCanvas();
        int[] dimensions = {bc.getDimenstion().width, bc.getDimenstion().height};
        System.out.println("DIMENSION IS: " + bc.getDimenstion().width + " BY "
                + bc.getDimenstion().height);
        ArrayList<Location> obstacles = new ArrayList<>();

        Location snakeHead = new Location(
                dimensions[0] / 2, dimensions[1] / 2);
        Level retLevel = new Level(dimensions, new Snake(snakeHead, 5), obstacles);
        retLevel.countSquares = 30;
        return retLevel;
    }

    /**
     * Constructs a special level using a file.
     * @param fileName Filename of a level file.
     */

    public static Level parseFile(String fileName) {
        System.out.println(fileName);
        if (fileName.equals("")) {
            return parseEmpty();
        }
        try {
            int[] dimensions = new int[2];
            ArrayList<Location> obstacles = new ArrayList<>();

            // Scan the lines of the inputfile
            File levelFile = new File("src/main/resources/" + fileName);
            InputStream levelStream = new FileInputStream(levelFile);
            BufferedReader scanner = new BufferedReader(
                    new InputStreamReader(levelStream, "UTF-8"));
            List<String> lines = new ArrayList<>();
            while (scanner.ready()) {
                lines.add(scanner.readLine());
            }
            dimensions[0] = lines.get(0).length();
            dimensions[1] = lines.size() - 1;

            // Initialize the snake on the left side of the head with the given length
            Location snakeHead = new Location(-1, -1);
            BoardCanvas bc = new BoardCanvas();
            int ratio = bc.getDimenstion().width / dimensions[1];

            for (int y = 0; y < dimensions[1]; y++) {
                String line = lines.get(y);
                if (line.length() != dimensions[0]) {
                    throw new IOException("Lines aren't of same size");
                }
                char obstacleChar = '#';
                char snakeChar = 's';
                for (int x = 0; x < dimensions[0]; x++) {
                    char curChar = line.charAt(x);
                    if (curChar == obstacleChar) {
                        obstacles.add(new Location(x * ratio + ratio / 2, y * ratio + ratio / 2));
                    } else if (curChar == snakeChar) {
                        snakeHead = new Location(x * ratio + ratio / 2, y * ratio + ratio / 2,
                                Direction.RIGHT);
                    }
                }
            }
            int initialLength = Integer.parseInt(String.valueOf(
                    lines.get(lines.size() - 1).charAt(0)));

            dimensions[0] = dimensions[0] * ratio;
            dimensions[1] = dimensions[1] * ratio;
            Level retLevel =  new Level(dimensions, new Snake(snakeHead, initialLength), obstacles);
            retLevel.countSquares = dimensions[0] / ratio;
            System.out.println("DIMENSTION ARE " + dimensions[0]);
            return retLevel;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Level file not found!");
            return parseEmpty();
        }
    }
}
