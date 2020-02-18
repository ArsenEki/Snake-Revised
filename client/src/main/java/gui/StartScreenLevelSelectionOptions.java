package gui;

import authentication.dialogs.UserService;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * This class houses the JComboBoxes used on the start screen.
 */
public class StartScreenLevelSelectionOptions {

    /**
     * Makes the mode selector.
     * @return combobox with level modes.
     */
    public static JComboBox<String> levelMode() {
        String[] modes = {"Smooth Snake", "Classic Nokia"};
        JComboBox snakeMode = new JComboBox(modes);
        return snakeMode;
    }

    /**
     * Makes the level selector.
     * @return combobox with level numbers.
     */
    public static JComboBox<String> levelLevel() {
        UserService userService = new UserService();
        Double currHighscore = userService.userHighscore(StartScreenNew.getUser());
        System.out.println(currHighscore);
        ArrayList<String> levels = new ArrayList<String>();
        levels.add("Level 1");

        if (currHighscore >= Scores.getLevel2Treshold()) {
            levels.add("Level 2");
        }

        if (currHighscore >= Scores.getLevel3Treshold()) {
            levels.add("Level 3");
        }
        String[] levels2 = levels.toArray(new String[levels.size()]);

        JComboBox levelNumber = new JComboBox(levels2);
        return levelNumber;
    }

    /**
     * Creates a combobox that allows the user to pick backgroundmusic.
     * @return ComoboBox with backgroundmusic
     */
    public static JComboBox<String> levelMusic() {
        String[] music = {
            "Happy",
            "HipHop",
            "Classic"
        };

        JComboBox levelMusic = new JComboBox<>(music);

        return levelMusic;
    }

}
