package gui;

import authentication.dialogs.DeleteUserScreen;
import authentication.dialogs.LoginScreen;
import authentication.dialogs.User;
import authentication.dialogs.UserService;
import board.Direction;
import board.level.Level;
import board.level.LevelParser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 * This class creates the screen in which the level modes and level layouts are selected.
 * This screen also shows a table with the current highscore ranking.
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.AvoidLiteralsInIfCondition",
        "PMD.ConstructorCallsOverridableMethod"})
public class StartScreenNew extends JDialog {

    public static final long serialVersionUID = 4328743;

    private transient JLabel selectLevelLabel;
    private transient JLabel highscoreLabel;
    private transient JLabel levelmodeLabel;
    private transient JLabel levelnumberLabel;
    private transient JLabel levelmusicLabel;
    private transient JPanel panel;
    private transient JPanel badgePanel;
    private transient JTable highscoresTable;
    private transient JComboBox<String> snakeMode;
    private transient JComboBox<String> snakeLevel;
    private transient JComboBox<String> snakeMusic;
    private transient JButton logoutButton;
    private transient JButton helpButton;
    private transient JButton startGameButton;
    private transient JButton deleteUserButton;
    private transient String resources = "src/main/resources/";
    private String levelFile = "Level1.txt";
    private String levelMusicSelect = "";

    private Color colorBackground = new Color(0, 50, 50);

    public static boolean classic = false;

    /**
     * Creates new form StartScreen.
     */
    public StartScreenNew(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private static User user;
    private transient JFrame frame;
    private transient UserService userService = new UserService();

    /**
     * This method will initialise the primary fields.
     */
    private void initialiseFields() {
        panel = new JPanel();
        highscoreLabel = new JLabel();
        selectLevelLabel = new JLabel();
        levelmodeLabel = new JLabel();
        levelnumberLabel = new JLabel();
        levelmusicLabel = new JLabel();
        highscoresTable = new JTable();
        snakeMode = new JComboBox<>();
        snakeMusic = new JComboBox<>();
        snakeLevel = new JComboBox<>();
        startGameButton = new JButton();
        helpButton = new JButton();
        deleteUserButton = new JButton();
        logoutButton = new JButton();
        badgePanel = new JPanel();
    }

    /**
     * This method will set the frame specifics.
     */
    private void setWindowParameters() {
        // Sets the frame specifics
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new Rectangle(0, 23, 600, 450));
        setPreferredSize(new Dimension(600, 450));
        setResizable(false);
        setSize(new Dimension(400, 300));
        getRootPane().setDefaultButton(startGameButton);
    }

    /**
     * This method will set up the labels and colors for the frame.
     */
    private void labelAndColorSettings() {
        // Sets the background specifics
        panel.setBackground(colorBackground);

        // Sets label for the level options
        selectLevelLabel = StartScreenComponentStyler.title(selectLevelLabel,
                "Select level options");

        // Sets label for the highscores table
        highscoreLabel = StartScreenComponentStyler.title(highscoreLabel, "Highscores");

        // Sets label for the level mode
        levelmodeLabel = StartScreenComponentStyler.title2(levelmodeLabel, "Level mode");

        // Sets label for the level number
        levelnumberLabel = StartScreenComponentStyler.title2(levelnumberLabel, "Level number");

        // Sets label for the level music
        levelmusicLabel = StartScreenComponentStyler.title2(levelmusicLabel, "Level music");

        // Sets up the level mode combobox
        snakeMode = StartScreenLevelSelectionOptions.levelMode();

        // Sets up the level number combobox
        snakeLevel = StartScreenLevelSelectionOptions.levelLevel();

        // Sets up the level music combobox
        snakeMusic = StartScreenLevelSelectionOptions.levelMusic();

        // Sets the table specifics
        highscoresTable = Scores.highscoreTable();
        highscoresTable = StartScreenComponentStyler.table(highscoresTable);
    }

    /**
     * This method will set the button specific settings.
     */
    private void buttonSpecifics() {
        // Sets the help buttons specifics
        helpButton = StartScreenComponentStyler.button(helpButton, "Help");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        // Sets te log out button specifics
        logoutButton = StartScreenComponentStyler.button(logoutButton, "Log Out");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        // Sets the delete user button
        deleteUserButton = StartScreenComponentStyler.button(deleteUserButton, "Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        // Sets the start game button specifics
        startGameButton = StartScreenComponentStyler.button(startGameButton, "Start Game");
        startGameButton.setMinimumSize(new Dimension(150,60));
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        initialiseFields();

        setWindowParameters();

        labelAndColorSettings();

        buttonSpecifics();


        // Sets the badge panel
        badgePanel.setBackground(colorBackground);
        Scores.addBadges(badgePanel);

        // The placement of the components
        GroupLayout groupLayout = new GroupLayout(panel);
        StartScreenComponentPlacement.placementGroupOne(panel, groupLayout, levelmusicLabel,
                levelmodeLabel, levelnumberLabel, selectLevelLabel, highscoreLabel, startGameButton,
                deleteUserButton, logoutButton, helpButton, highscoresTable, badgePanel, snakeMode,
                snakeLevel, snakeMusic);
        StartScreenComponentPlacement.placementGroupTwo(groupLayout, selectLevelLabel,
                levelmodeLabel, highscoreLabel, levelnumberLabel, levelmusicLabel,
                helpButton, deleteUserButton, logoutButton, startGameButton,
                highscoresTable, snakeMusic, snakeMode, snakeLevel, badgePanel);
        placementGroupThree();
    }

    /**
     * This method will set up the placement of the components.
     */
    private void placementGroupThree() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Shows a screen with relevant information for the client.
     * @param evt evt.
     */
    private void helpButtonActionPerformed(ActionEvent evt) {
        InfoScreen infoScreen = new InfoScreen(frame, true);
        infoScreen.setVisible(true);
    }

    /**
     * Closes the start screen and takes client to the screen to delete the profile.
     * @param evt evt.
     */
    private void deleteUserButtonActionPerformed(ActionEvent evt) {
        // Creates and shows a DeleteUserScreen.
        dispose();
        DeleteUserScreen deleteUserScreen = new DeleteUserScreen(frame, true);
        deleteUserScreen.setVisible(true);
    }

    /**
     * Closes the start screen and takes client back to the login page.
     * @param evt evt.
     */
    private void logoutButtonActionPerformed(ActionEvent evt) {
        // Creates and shows LoginScreen.
        this.dispose();
        LoginScreen loginScreen = new LoginScreen(frame, true);
        loginScreen.setLocationRelativeTo(getParent());
        loginScreen.setVisible(true);
        loginScreen.setSucceeded(false);
        dispose();

        // If login succeeded, LoginScreen is closed and StartScreen is shown.
        if (loginScreen.isSucceeded()) {
            frame.setVisible(false);
            StartScreenNew startScreenNew = new StartScreenNew(frame, true);
            startScreenNew.setVisible(true);
        }
    }

    /**
     * Starts a new game with the preferences of the client.
     * @param evt evt.
     */
    public void startGameButtonActionPerformed(ActionEvent evt) {
        dispose();

        // Switch statement to determine which mode and level the user chose
        setSnakeLookOptions();

        setLevelOptions();

        setMusicOptions();

        // Creates a level with the given level file.
        Level level = LevelParser.parseFile(levelFile);
        // Creates a MainGameWindow with the created level.
        AudioHandling.setBackgroundAudioName(levelMusicSelect);
        MainGameWindow mainGameWindow = new MainGameWindow(level);
        mainGameWindow.setVisible(true);

        // Adds key listeners for the different directions.
        mainGameWindow.getBoardPane().addKeyListener(new KeyListener() {


            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37:
                        mainGameWindow.getBoardCanvas().setSnakeDir(Direction.LEFT);
                        //snake.setDirection(Direction.LEFT);
                        break;
                    case 38:
                        //snake.setDirection(Direction.UP);
                        mainGameWindow.getBoardCanvas().setSnakeDir(Direction.UP);
                        break;
                    case 39:
                        //snake.setDirection(Direction.RIGHT);
                        mainGameWindow.getBoardCanvas().setSnakeDir(Direction.RIGHT);
                        break;
                    case 40:
                        //snake.setDirection(Direction.DOWN);
                        mainGameWindow.getBoardCanvas().setSnakeDir(Direction.DOWN);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    /**
     * This method will set the option for smooth or classic snake.
     */
    private void setSnakeLookOptions() {
        BufferedImageLoader loader = new BufferedImageLoader();
        switch (snakeMode.getSelectedIndex()) {
            case 1:
                try {
                    RenderEngine.obstacleSprite = loader
                            .loadImage("res/Bricks-Classic.png");
                    RenderEngine.spriteSheet = loader
                            .loadImage("res/Snake_Sprite_Plain.png");
                    RenderEngine.background = new Color(154, 197, 3);
                    classic = true;
                    //System.out.println("---CLASSIC SNAKE---");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case 0:
                try {
                    RenderEngine.obstacleSprite = loader
                            .loadImage("res/Bricks.png");
                    RenderEngine.spriteSheet = loader
                            .loadImage("res/Snake_Sprite_Scaled.png");
                    RenderEngine.background = new Color(0, 0, 0);
                    classic = false;
                    //System.out.println("---SMOOTH SNAKE---");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    /**
     * This method will set the options for the selected level.
     */
    private void setLevelOptions() {

        switch (snakeLevel.getSelectedIndex()) {
            case 0:
                break;
            case 1:
                levelFile = "Level2.txt";
                break;
            case 2:
                levelFile = "Level3.txt";
                break;
            default:
                break;
        }
    }

    /**
     * This method will set the music option for the level.
     */
    private void setMusicOptions() {
        switch (snakeMusic.getSelectedIndex()) {
            case 0:
                levelMusicSelect = "happy.wav";
                break;
            case 1:
                levelMusicSelect = "hiphop.wav";
                break;
            default:
                levelMusicSelect = "classic.wav";
                break;
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        StartScreenNew.user = user;
    }

}
