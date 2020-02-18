package gui;

import board.Direction;
import board.Location;
import board.level.Level;
import board.level.LevelStatus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.AvoidLiteralsInIfCondition"})
public class BoardCanvas extends Canvas implements Runnable {

    private static final int width = GameDimensions.width;
    private static final int height = GameDimensions.height; // / 12 * 9;
    private static final int scale = GameDimensions.scale;



    private BufferStrategy bufferStrategy;



    private Level gameLevel;


    private Direction newDir = Direction.RIGHT;

    //private int ticks = -1;

    private LinkedList<Integer> bodyRot;

    private boolean running = false;
    private GameClock gameThread;

    private int updates = 0;

    private RenderEngine renderEngine;

    /**
     * This method will start the game clock.
     */
    public synchronized void start() {
        if (running) {
            return;
        }
        System.out.println("SETTING UP THREAD");
        running = true;
        gameThread = GameClock.getInstance(this);
        gameThread.start();
    }

    /**
     * This method will stop the game clock.
     */
    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("RAN METHOD");
        long latTime = System.nanoTime();
        final double amountOfTicks = 20.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {


            long now = System.nanoTime();
            delta += (now - latTime) / ns;
            latTime = now;



            if (delta >= 1) {
                renderEngine.render(bufferStrategy);
                handleGameUpdateLogic();

                //ticks = updates;
                updates++;
                delta--;
            }

            //frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " Ticks , FPS " + frames);

                //frames = 0;
                updates = 0;
            }

        }
        stop();
    }

    /**
     * This method will check when and how to update the game logic.
     */
    private void handleGameUpdateLogic() {
        if (gameLevel.isInProgress() && updates % 4 == 0
                && gameLevel.getGameStatus() != LevelStatus.PAUSED) {

            tick(updates);

            //gameLevel.getSnake().directBody();
            gameLevel.getSnake().setDirection(this.newDir);

            gameLevel.checkSelfCollision();
        }
        if (!gameLevel.isInProgress()
                && gameLevel.getGameStatus() != LevelStatus.RESTARTED) {
            updateMainStatuses();
            renderEngine.render(bufferStrategy);

        }
        if (!gameLevel.isInProgress()
                && gameLevel.getGameStatus() == LevelStatus.RESTARTED) {
            this.newDir = Direction.RIGHT;
        }
    }

    /**
     * This method will update statuses related to the MainGameWindow.
     */
    private void updateMainStatuses() {
        AudioHandling.pauseResumeBackground(false);
        MainGameWindow.getBoardPane().getStartButton().setText("Restart");
    }

    /**
     * This method will update the current game level logic.
     * @param tick amount of ticks passsed this second.
     */
    private void tick(int tick) {
        if (gameLevel.getGameStatus() == LevelStatus.STARTED) {
            gameLevel.update(tick);
            updateDisplayedGameScore();

            gameEndPopup();
        }

    }

    /**
     * This method will update the displayed score on the game screen.
     */
    private void updateDisplayedGameScore() {
        int gameScore = MainGameWindow.getGameLevel().getScore();
        BoardPane.setScore(gameScore);
    }

    /**
     * This method will check if the game has ended and if so will display a popup.
     */
    private void gameEndPopup() {
        if (!gameLevel.isInProgress()) {
            BoardPane.showSettings();
        }
    }

    /**
     * This method will return the dimension of the game screen.
     * @return the width and the height of the game screen.
     */
    public Dimension getDimenstion() {
        return new Dimension(width * scale, height * scale);
    }

    /**
     * This method will set the snake's head direction.
     * @param dir new direction of the snake head.
     */
    public void setSnakeDir(Direction dir) {
        this.newDir = dir;
    }

    /**
     * This method is an empty consctructor for functionality.
     */
    public BoardCanvas() {

    }


    /**
     * This methos will initialise the buffer strategy for the image buffers.
     */
    public void initBS() {
        createBufferStrategy(3);
        bufferStrategy = this.getBufferStrategy();
    }




    /**
     * This method will initalise a new board canvas.
     * @param gameLevel the game level that the current game is working with.
     */
    public BoardCanvas(Level gameLevel) {
        this.gameLevel = gameLevel;

        this.setMaximumSize(new Dimension(width * scale, height * scale));
        this.setMinimumSize(new Dimension(width * scale, height * scale));
        this.setPreferredSize(new Dimension(width * scale, height * scale));
        renderEngine = new RenderEngine(gameLevel);


    }
}
