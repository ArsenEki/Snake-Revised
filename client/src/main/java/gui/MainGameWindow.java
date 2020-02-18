package gui;

import board.level.Level;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.UseProperClassLoader"})
public class MainGameWindow extends JFrame  {


    static JButton startButton;
    static JPanel board;
    static boolean isStarted = false;
    static int boardX;
    static int boardY;
    private static Level gameLevel;
    private BoardCanvas boardCanvas;
    private static BoardPane boardPane;
    private static JPanel[][] dispBoard;

    public static final String TITLE = "SNAKE SEM";





    /**
     * Constructor for the class gui.MainGameWindow.
     * @param gameLevel the board for that window
     */
    public MainGameWindow(Level gameLevel) {
        super(TITLE);

        this.gameLevel = gameLevel;

        boardX = gameLevel.getBoardX();
        boardY = gameLevel.getBoardX();

        //dispBoard = new JPanel[boardX][boardY];
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //dispBoard = new JPanel[boardX][boardY];
        this.setLayout(new GridBagLayout());

        boardPane = new BoardPane(this);

        startButton = boardPane.getStartButton();

        this.gameLevel = gameLevel;


        this.boardCanvas = new BoardCanvas(gameLevel);

        this.add(boardCanvas);
        boardCanvas.initBS();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        this.add(boardPane, gbc);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        //boardCanvas.render();

        AudioHandling audioHandling = new AudioHandling();
        audioHandling.setUpAudio();

        //boardCanvas.sartrender();
        boardCanvas.start();
        System.out.println("STARTED");

    }

    public BoardCanvas getBoardCanvas() {
        return this.boardCanvas;
    }



    public static Level getGameLevel() {
        return gameLevel;
    }



    public static BoardPane getBoardPane() {

        return boardPane;
    }

    public static void setLevel(Level level) {
        gameLevel = level;
    }


}
