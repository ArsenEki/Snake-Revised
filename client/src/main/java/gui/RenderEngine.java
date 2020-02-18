package gui;

import board.Direction;
import board.Location;
import board.level.Level;
import board.level.LevelStatus;

import java.awt.Canvas;
import java.awt.Color;
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
public class RenderEngine extends Canvas {

    public static Color background = new Color(0, 0, 0);
    public static BufferedImage spriteSheet = null;
    public static BufferedImage obstacleSprite = null;
    private BufferedImage border = null;
    private BufferedImage player;
    //private BufferStrategy bufferStrategy;
    private Level gameLevel;
    private static final int width = GameDimensions.width;
    private static final int height = GameDimensions.height; // / 12 * 9;
    private static final int scale = GameDimensions.scale;

    RenderEngine(Level gameLevel) {
        this.gameLevel = gameLevel;
        init();
    }

    /**
     * This method will initialise the sprites for the game.
     */
    public final void init() {
        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            //spriteSheet = loader.loadImage("res/Snake_Sprite_Scaled.png");

            border = loader.loadImage("res/WallSprite.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        SpriteSheet ss = new SpriteSheet(spriteSheet);
        player = ss.grabImage(1, 1, GraphicsBounds.spriteSize, GraphicsBounds.spriteSize,
                GraphicsBounds.spriteSize);
    }

    /**
     * This methos will get a specific sprite from a combined one.
     * @param col column of the sprite.
     * @param row row of the sprite.
     */
    public void getPartOfSprite(int col, int row) {
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        player = ss.grabImage(col, row, GraphicsBounds.spriteSize, GraphicsBounds.spriteSize,
                GraphicsBounds.spriteSize);
    }




    /**
     * This method will draw the borders for the game screen.
     * @param g the graphics to draw with
     */
    private void drawBorders(Graphics g) {
        for (int i = 0; i < width * scale; i++) {
            for (int j = 0; j < height * scale; j++) {
                if (i == 0 || j == 0 || i + 1 == width * scale + 1 - GraphicsBounds.spriteSize
                        || j + 1 == height * scale + 2 - GraphicsBounds.spriteSize) {
                    g.drawImage(border, i, j, this);
                }
            }
        }
    }

    /**
     * This method will draw all the entitties the snake can collide with.
     * @param g the graphisc to draw with.
     */
    private void drawCollidables(Graphics g) {
        ArrayList<Location> obstacles = gameLevel.getObstacles();

        g.drawImage(gameLevel.getEdible().getSprite(), gameLevel.getEdibleLoc().locX,
                gameLevel.getEdibleLoc().locY, this);

        for (int i = 0; i < obstacles.size(); i++) {
            g.drawImage(obstacleSprite, obstacles.get(i).locX,
                    obstacles.get(i).locY, this);
        }
    }

    /**
     * This method will play the corresponding sound on a snake move.
     * @param snakeBody the body of the snake to get orientation from.
     */
    private void moveWithSound(LinkedList<Location> snakeBody) {
        if (snakeBody.get(0).direction != snakeBody.get(1).direction
                && gameLevel.isInProgress()) {
            if (snakeBody.get(0).direction.equals(Direction.DOWN)
                    || snakeBody.get(0).direction.equals(Direction.UP)) {
                AudioHandling.playTurn1Sound();
            } else {
                AudioHandling.playTurn2Sound();
            }
        }
    }

    /**
     * This method will selec the proper corner sprite to be rendered.
     * @param bodyPart1 the first part of the snake body to be compared.
     * @param bodyPart2 the second part of the snake body to be compared.
     */
    private void selecCornerSprite(Location bodyPart1, Location bodyPart2) {
        switch (bodyPart1.direction) {
            case RIGHT:
                if (bodyPart2.direction == Direction.UP) {
                    getPartOfSprite(2, 1);
                } else if (bodyPart2.direction == Direction.DOWN) {
                    getPartOfSprite(2, 2);
                }
                break;
            case LEFT:
                if (bodyPart2.direction == Direction.UP) {
                    getPartOfSprite(3, 1);
                } else if (bodyPart2.direction == Direction.DOWN) {
                    getPartOfSprite(3, 2);
                }
                break;
            case UP:
                if (bodyPart2.direction == Direction.LEFT) {
                    getPartOfSprite(2, 2);
                } else if (bodyPart2.direction == Direction.RIGHT) {
                    getPartOfSprite(3, 2);
                }
                break;
            case DOWN:
                if (bodyPart2.direction == Direction.LEFT) {
                    getPartOfSprite(2, 1);
                } else if (bodyPart2.direction == Direction.RIGHT) {
                    getPartOfSprite(3, 1);
                }
                break;
            default:
                break;
        }
    }

    /**
     * This method will draw the snake on screen.
     * @param g the graphics to draw with.
     */
    private void drawSnake(Graphics g) {
        LinkedList<Location> snakeBody = gameLevel.getSnake().getBody();
        ArrayList<Location> corers = new ArrayList<>();
        ArrayList<Integer> spriteCorners = new ArrayList<>();
        for (int i = 0; i < snakeBody.size(); i++) {
            double rotationRequired = Math.toRadians(snakeBody.get(i).getRotation());

            //System.out.println("SNAKE PART ROTATION IS: " + snakeBody.get(i).getRotation());
            AffineTransform tx = AffineTransform.getRotateInstance(
                    rotationRequired, GraphicsBounds.spriteSize / 2,
                    GraphicsBounds.spriteSize / 2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            if (i == 0) {
                getPartOfSprite(1, 1);

                g.drawImage(op.filter(player, null), snakeBody.get(i).locX,
                        snakeBody.get(i).locY, this);
                moveWithSound(snakeBody);

            } else if (snakeBody.get(i - 1).direction != snakeBody.get(i).direction) {
                selecCornerSprite(snakeBody.get(i - 1), snakeBody.get(i));
                g.drawImage(player, snakeBody.get(i).locX,
                        snakeBody.get(i).locY, this);

            } else if (i + 1 == snakeBody.size()) {
                getPartOfSprite(2, 3);
                g.drawImage(op.filter(player, null), snakeBody.get(i).locX,
                        snakeBody.get(i).locY, this);
            }  else {
                getPartOfSprite(3, 3);
                g.drawImage(op.filter(player, null), snakeBody.get(i).locX,
                        snakeBody.get(i).locY, this);
            }


        }

    }


    /**
     * This method will render the game screen.
     */
    public void render(BufferStrategy bufferStrategy) {
        LevelStatus status = gameLevel.getGameStatus();


        Graphics g = bufferStrategy.getDrawGraphics();

        //g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setColor(background);
        g.fillRect(0, 0, width * scale, height * scale);
        //if (true || gameLevel.isInProgress()) {

        //////////////////////////////////////
        drawBorders(g);

        drawCollidables(g);

        drawSnake(g);
        /////////////////////////////////////
        g.dispose();
        bufferStrategy.show();
        //}



    }
}
