package gui;

import authentication.dialogs.User;
import authentication.dialogs.UserService;
import board.level.Level;

import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


/**
 * This class hold the logic to handle all score related operations.
 * E.g. as checking and setting user highscores.
 */
@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class Scores {

    private static Level gameLevel;
    private static transient double level2Treshold = 5;
    private static transient double level3Treshold = 10;
    private static transient String resources = "src/main/resources/";
    private static UserService userService = new UserService();

    public static double getLevel2Treshold() {
        return level2Treshold;
    }

    public static double getLevel3Treshold() {
        return level3Treshold;
    }

    /**
     * Makes table listing the top 5 players.
     * @return the table.
     */
    public static final JTable highscoreTable() {
        String list = userService.highscores();
        //String list = "[admin 420.0, user4 40.0, user3 30.0, user2 20.0, user1 10.0]";
        System.out.println("1 " + list);
        list = list.substring(1);
        System.out.println("2 " + list);

        // Split the provided string into useful values.
        Scanner s = new Scanner(list);
        String user1 = s.next();
        String highscore1 = s.next();
        String user2 = s.next();
        String highscore2 = s.next();
        String user3 = s.next();
        String highscore3 = s.next();
        String user4 = s.next();
        String highscore4 = s.next();
        String user5 = s.next();
        String highscore5 = s.next();
        s.close();

        // Remove redundant characters from hisghscore value.
        highscore1 = highscore1.substring(0, highscore1.length() - 3);
        highscore2 = highscore2.substring(0, highscore2.length() - 3);
        highscore3 = highscore3.substring(0, highscore3.length() - 3);
        highscore4 = highscore4.substring(0, highscore4.length() - 3);
        highscore5 = highscore5.substring(0, highscore5.length() - 3);

        //Sets up the table with the highscore values.
        String[] columnNames = {"Userame", "Highscore"};
        Object[][] data = {
                {user1, highscore1},
                {user2, highscore2},
                {user3, highscore3},
                {user4, highscore4},
                {user5, highscore5}
        };

        final JTable table = new JTable(
                new Object[][] {
                    {user1, highscore1},
                    {user2, highscore2},
                    {user3, highscore3},
                    {user4, highscore4},
                    {user5, highscore5}
                },
                new String[] {
                    "Username", "Highscore"
                });
        return table;
    }

    /**
     * This method will check for the user's high score.
     */
    public static void checkHighScore(Double highscore, JPanel panel) {
        if (highscore >= 0.0 && highscore < level2Treshold) {
            ImageIcon first = new ImageIcon(resources + "first.png");
            JLabel label = new JLabel(first);
            label.setToolTipText("You unlocked the first level, earn a highscore of "
                    + level2Treshold + " to unlock level 2!");
            panel.add(label);
            System.out.println("First level unlocked badge awarded.");
        }
        if (highscore >= level2Treshold && highscore < level3Treshold) {
            ImageIcon second = new ImageIcon(resources + "second.png");
            JLabel label = new JLabel(second);
            label.setToolTipText("You unlocked the second level, earn a highscore of "
                    + level3Treshold + " to unlock level 4!");
            label.setToolTipText("<html>"
                    + "You unlocked the second level,"
                    + "<br>"
                    + "earn a highscore of " + level3Treshold
                    + "<br>"
                    + " to unlock level 4!"
                    + "</html>");
            panel.add(label);
            System.out.println("Second level unlocked badge awarded.");

        }
        if (highscore >= level3Treshold) {
            ImageIcon third = new ImageIcon(resources + "third.png");
            JLabel label = new JLabel(third);
            label.setToolTipText("<html>"
                    + "You unlocked the third and last level!"
                    + "<br>"
                    + "Good job :)"
                    + "</html>");
            panel.add(label);
            System.out.println("Third level unlocked badge awarded.");
        }
    }

    /**
     * Sets the users highscore upon death.
     */
    public static void setHighscoreUser(int score, UserService userService) {
        if (StartScreenNew.getUser() == null) {
            return;
        }
        StartScreenNew.getUser().setHighscore(score);
        if (userService.sendUserToServer(StartScreenNew.getUser(), "/gethighscore")) {
            userService.sendUserToServer(StartScreenNew.getUser(), "/sethighscore");
            System.out.println("Setting the user " + StartScreenNew.getUser().getUserName()
                    + "'s highscore to " + StartScreenNew.getUser().getHighscore());
            return;
        } else {
            System.out.println("New score wasn't higher than previous highscore.");
        }
    }

    /**
     * Method responsible for checking the user's highscore and adding
     * the relevant badges to the user's startscreen.
     * For now we have the badges for the top 3 players and for players
     * with a score above 0, 5 and 10.
     */
    public static void addBadges(JPanel panel) {
        User user = StartScreenNew.getUser();
        //To be used for certain points badges.
        Double highscore = userService.userHighscore(user);
        String list = userService.highscores();
        Scanner s = new Scanner(list);
        String user1 = s.next().substring(1);
        //String user1 = user0.substring(1);
        s.next();
        String user2 = s.next();
        s.next();
        String user3 = s.next();
        s.close();
        if (user.getUserName().equals(user1)) {
            ImageIcon first = new ImageIcon(resources + "gold.png");
            JLabel label = new JLabel(first);
            label.setToolTipText("You are the best player worldwide!");

            panel.add(label);

            System.out.println("Top player badge awarded");
        }
        if (user.getUserName().equals(user2)) {
            ImageIcon second = new ImageIcon(resources + "silver.png");
            JLabel label = new JLabel(second);
            label.setToolTipText("You are the second best player worldwide!");

            panel.add(label);
            System.out.println("Second best player badge awarded");

        }
        if (user.getUserName().equals(user3)) {
            ImageIcon third = new ImageIcon(resources + "bronze.png");
            JLabel label = new JLabel(third);
            label.setToolTipText("You are the third best player worldwide!");
            panel.add(label);
            System.out.println("Third best player badge awarded");
        }
        Scores.checkHighScore(highscore, panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

}
