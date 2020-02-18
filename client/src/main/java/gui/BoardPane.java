package gui;

import static gui.MainGameWindow.board;
import static gui.MainGameWindow.boardX;
import static gui.MainGameWindow.boardY;
import static javax.swing.JFrame.isDefaultLookAndFeelDecorated;

import authentication.dialogs.UserService;
import board.level.Level;
import board.level.LevelStatus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic"})
public class BoardPane extends JPanel {
    private static JButton startButton;
    private static JButton muteButton;
    private static JTextArea scoreDisplay;
    private static boolean gameMusicPlaying = true;

    private static JTextPane highScore;
    private static JButton resume;

    static MainGameWindow mainGameWindow;
    private static JFrame settings;

    private static Level gameLevel;

    private GameClock gameClock;

    public JButton getStartButton() {
        return this.startButton;
    }

    public JPanel getBoard() {
        return board;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public JTextArea getScore() {
        return this.scoreDisplay;
    }

    public static void setScore(int score) {
        scoreDisplay.setText("" + score);
    }

    /**
     * Constructor for the class BoardPane.
     */
    public BoardPane(MainGameWindow mainGameWindow) {

        this.setMaximumSize(new Dimension(125, 200));
        this.setMinimumSize(new Dimension(125, 200));
        this.setPreferredSize(new Dimension(125, 200));


        this.mainGameWindow = mainGameWindow;



        gameLevel = mainGameWindow.getGameLevel();
        boardX = gameLevel.getBoardX();
        boardY = gameLevel.getBoardX();

        initSettingsMenu();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.weightx = 5;
        board = new JPanel();
        board.setLayout(new GridLayout(boardY, boardX));
        board.setSize(boardX, boardY);
        add(board, gbc);


        JPanel sidemenu = new JPanel();
        sidemenu.setLayout(new GridBagLayout());
        GridBagConstraints sideMconstraints = new GridBagConstraints();
        sideMconstraints.gridx = 0;
        sideMconstraints.gridy = 0;
        sidemenu.setBackground(new Color(0, 50, 50));
        sidemenu.setSize(400, boardY);
        gbc.gridx = boardX;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 50;
        //gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridwidth = 20;
        add(sidemenu, gbc);

        // Sets up the start button
        startButton = new JButton("Start");
        startButton.setBackground(new Color(0, 70, 50));
        startButton.setForeground(new Color(40, 180, 100));
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.setMinimumSize(new Dimension(100,30));
        startButton.setSize(100, 100);

        // Sets up the mute button
        muteButton = new JButton("Mute");
        muteButton.setBackground(new Color(0, 70, 50));
        muteButton.setForeground(new Color(40, 180, 100));
        muteButton.setOpaque(true);
        muteButton.setBorderPainted(false);
        muteButton.setMinimumSize(new Dimension(100,30));
        muteButton.setSize(100, 100);


        scoreDisplay = new JTextArea();

        scoreDisplay.setText("0");
        scoreDisplay.setFont(new Font("Lucida Grande", 0, 15));
        scoreDisplay.setForeground(new Color(0, 170,70));
        scoreDisplay.setBackground(new Color(0, 50, 50));

        sidemenu.add(muteButton,sideMconstraints);
        sideMconstraints.gridx = 0;
        sideMconstraints.gridy  = 2;
        sidemenu.add(startButton, sideMconstraints);
        sideMconstraints.gridx = 0;
        sideMconstraints.gridy  = 3;
        sidemenu.add(scoreDisplay, sideMconstraints);

        sidemenu.setMaximumSize(new Dimension(100, 500));
        sidemenu.setMinimumSize(new Dimension(100, 500));
        sidemenu.setPreferredSize(new Dimension(100, 500));

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        muteButton.setAlignmentX(CENTER_ALIGNMENT);
        scoreDisplay.setAlignmentX(CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainGameWindow.setEnabled(false);
                if (startButton.getText().equals("Start")) {

                    mainGameWindow.setEnabled(true);
                    gameLevel.setInProgress(true);
                    mainGameWindow.getGameLevel().setGameStatus(LevelStatus.STARTED);

                    if (AudioHandling.getGameMusicPlaying()) {
                        AudioHandling.pauseResumeBackground(true);
                        muteButton.setText("Mute");
                    } else {
                        AudioHandling.pauseResumeBackground(false);
                        muteButton.setText("Play Music");
                    }

                    mainGameWindow.getBoardPane().grabFocus();
                    startButton.setText("Pause");

                    System.out.println("GAME STARTED");
                    gameClock = GameClock.getInstance(mainGameWindow.getBoardCanvas());


                } else if (startButton.getText().equals("Resume")) {

                    mainGameWindow.setEnabled(true);
                    gameLevel.setInProgress(true);
                    mainGameWindow.getGameLevel().setGameStatus(LevelStatus.STARTED);
                    mainGameWindow.getBoardPane().grabFocus();
                    startButton.setText("Pause");

                    if (AudioHandling.getGameMusicPlaying()) {
                        AudioHandling.pauseResumeBackground(true);
                        muteButton.setText("Mute");
                    } else {
                        AudioHandling.pauseResumeBackground(false);
                        muteButton.setText("Play Music");
                    }

                    System.out.println("GAME STARTED");
                    gameClock = GameClock.getInstance(mainGameWindow.getBoardCanvas());


                } else if (startButton.getText().equals("Pause")) {
                    showSettings();
                    mainGameWindow.setEnabled(false);
                    mainGameWindow.getGameLevel().setGameStatus(LevelStatus.PAUSED);
                    AudioHandling.pauseResumeBackground(false);
                    muteButton.setText("Play Music");
                    startButton.setText("Resume");

                    mainGameWindow.getBoardPane().grabFocus();

                } else if (startButton.getText().equals("Restart")) {

                    mainGameWindow.setEnabled(true);
                    startButton.setText("Start");
                    restartLevel();
                    AudioHandling.pauseResumeBackground(true);
                    muteButton.setText("Mute");
                    mainGameWindow.getGameLevel().setGameStatus(LevelStatus.RESTARTED);
                    mainGameWindow.getBoardPane().grabFocus();
                    //isStarted = true;

                }
            }
        });

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (muteButton.getText().equals("Play Music")) {
                    AudioHandling.pauseResumeBackground(true);
                    muteButton.setText("Mute");
                    AudioHandling.setGameMusicPlaying(true);
                    mainGameWindow.getBoardPane().grabFocus();
                } else if (muteButton.getText().equals("Mute")) {
                    AudioHandling.pauseResumeBackground(false);
                    muteButton.setText("Play Music");
                    AudioHandling.setGameMusicPlaying(false);
                    mainGameWindow.getBoardPane().grabFocus();
                }
            }
        });

    }

    /**
     * This method will reset the level to initial status.
     */
    public static  void restartLevel() {
        muteButton.setText("Play Music");
        gameLevel.initialise();
        setScore(0);
        mainGameWindow.setEnabled(true);
    }

    /**
     * This method will initialise the settings menu.
     */
    public static void initSettingsMenu() {
        settings = new JFrame("Settings");
        settings.setAlwaysOnTop(true);

        JPanel settingsList = new JPanel();
        settingsList.setBackground(new Color(0, 50, 50));

        settingsList.setLayout(new BoxLayout(settingsList, BoxLayout.PAGE_AXIS));
        settings.add(settingsList);

        StyledDocument document = updateMenuScore();

        highScore = new JTextPane(document);
        //highScore.setText(document);
        highScore.setAlignmentX(CENTER_ALIGNMENT);
        highScore.setEditable(false);
        highScore.setBackground(new Color(0, 50, 50));
        highScore.setForeground(new Color(40, 180, 100));
        highScore.setFont(new Font("Lucida Grande", 0, 15));
        settingsList.add(highScore);



        //        try {
        //            document.insertString(document.getLength(), "Current Score \n"
        //                    + String.valueOf(gameLevel.getScore()), style);
        //        } catch (BadLocationException e) {
        //            e.printStackTrace();
        //        }

        JPanel fillerTop = new JPanel();
        fillerTop.setBackground(new Color(0, 50, 50));
        settingsList.add(fillerTop);

        resume = new JButton("Resume");
        resume.setBackground(new Color(0, 70, 50));
        resume.setForeground(new Color(40, 180, 100));
        resume.setOpaque(true);
        resume.setBorderPainted(false);
        resume.setMinimumSize(new Dimension(100,30));
        resume.setAlignmentX(CENTER_ALIGNMENT);

        if (gameLevel.isInProgress()) {
            settingsList.add(resume);
        }

        JPanel fillerMiddle = new JPanel();
        fillerMiddle.setBackground(new Color(0, 50, 50));
        settingsList.add(fillerMiddle);

        // Sets up the restart button on the settings page
        JButton restart = new JButton("Restart");
        restart.setBackground(new Color(0, 70, 50));
        restart.setForeground(new Color(40, 180, 100));
        restart.setOpaque(true);
        restart.setBorderPainted(false);
        restart.setMinimumSize(new Dimension(100,30));
        restart.setAlignmentX(CENTER_ALIGNMENT);
        settingsList.add(restart, Component.CENTER_ALIGNMENT);

        // Sets up the main menu button on the settings page
        JButton mainMenu = new JButton("Main Menu");
        mainMenu.setBackground(new Color(0, 70, 50));
        mainMenu.setForeground(new Color(40, 180, 100));
        mainMenu.setOpaque(true);
        mainMenu.setBorderPainted(false);
        mainMenu.setMinimumSize(new Dimension(100,30));
        mainMenu.setAlignmentX(CENTER_ALIGNMENT);
        settingsList.add(mainMenu, Component.CENTER_ALIGNMENT);

        settings.setMaximumSize(new Dimension(125, 250));
        settings.setMinimumSize(new Dimension(125, 250));
        settings.setPreferredSize(new Dimension(125, 250));

        settings.pack();
        settings.setResizable(false);
        settings.setLocationRelativeTo(mainGameWindow);


        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(false);
                gameLevel.setInProgress(true);
                if (AudioHandling.getGameMusicPlaying()) {
                    AudioHandling.pauseResumeBackground(true);
                    muteButton.setText("Mute");
                } else {
                    AudioHandling.pauseResumeBackground(false);
                    muteButton.setText("Play Music");
                }
                MainGameWindow.getGameLevel().setGameStatus(LevelStatus.STARTED);
                startButton.setText("Pause");
                mainGameWindow.setEnabled(true);
            }
        });

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(false);
                startButton.setText("Start");
                restartLevel();
                mainGameWindow.getGameLevel().setGameStatus(LevelStatus.RESTARTED);
                mainGameWindow.getBoardPane().grabFocus();
                mainGameWindow.setEnabled(true);
            }
        });

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disposes the settings screen and game screen
                settings.dispose();
                GameClock.resetInstance();
                mainGameWindow.getGameLevel().setGameStatus(LevelStatus.INITIALISED);
                restartLevel();
                mainGameWindow.getGameLevel().setGameStatus(LevelStatus.RESTARTED);
                mainGameWindow.getBoardPane().grabFocus();
                mainGameWindow.dispose();

                // Creates and shows a new startscreen.
                JFrame frame = new JFrame("Sssnake");
                frame.setVisible(false);
                StartScreenNew ss = new StartScreenNew(frame, true);
                ss.setVisible(true);

                //mainGameWindow = null;

            }
        });
    }

    /**
     * This method will show the settings menu.
     */
    public static void showSettings() {
        initSettingsMenu();
        highScore.setDocument(updateMenuScore());
        settings.setVisible(true);
    }

    /**
     * This method will hide the settings menu.
     */
    public static void hideSettings() {
        settings.setVisible(false);
    }

    /**
     * This method will update the score in the setting menu.
     * @return the document with the updated score values.
     */
    public static StyledDocument updateMenuScore() {
        UserService us = new UserService();
        StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        try {
            document.insertString(document.getLength(), "High Score \n"
                    + String.valueOf(us.userHighscore(StartScreenNew.getUser())
                    + "\n" + "Current Score \n"
                    + String.valueOf(gameLevel.getScore())), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return document;
    }

}