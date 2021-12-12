package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.ball.BallFactory;
import com.game.comp2042_cw_hcyot1.ball.BallType;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.CrackType;
import com.game.comp2042_cw_hcyot1.wall.WallHandler;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Handles Ball-Player-Wall interaction, but leaves as much detail
 * about the levels and their organisation and state to {@link #wallHandler} as possible.
 *
 * Anything that needs to be shown on screen is handled by {@link #controller}, to achieve separation of concerns.
 * @see GameView
 * @see GameController
 */
public class GameModel {
    private final Random rnd;
    private final Rectangle area;

    private final Ball ball;
    private final Player player;

    private final Point startPoint;
    private final GameController controller;
    /**
     * {@link #wallHandler} handles all the level and brick mechanics.
     */
    private final WallHandler wallHandler;
    private final Timer gameTimer;
    private final ScoreHandler scoreHandler = new ScoreHandler();
    private int ballCount;
    private boolean ballLost;
    private boolean isPaused;
    private int score = 0;

    /**
     * Initializes fields and game objects ({@link Ball}, {@link Player}, {@link WallHandler})
     *
     * @param drawArea   Area that the game takes place in
     * @param ballPos    Initial ball position
     * @param controller {@link GameController}
     */
    public GameModel(Rectangle drawArea, Point ballPos, GameController controller) {
        this.startPoint = new Point(ballPos);
        this.controller = controller;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        ball = BallFactory.makeBall(ballPos, BallType.RAINBOW);
        ball.setSpeed(randomSpeedX(), randomSpeedY());

        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;

        wallHandler = new WallHandler(drawArea.getWidth());

        isPaused = false;

        gameTimer = new Timer(10, e -> initializeTimer());
    }

    /**
     * Used by the {@link #gameTimer} to move the ball and player
     */
    public void move() {
        player.move();
        ball.move();
    }

    /**
     * Used to pause the game and stop the {@link #gameTimer}
     */
    public void pauseGame() {
        isPaused = true;
        gameTimer.stop();
    }

    public void unPauseGame() {
        isPaused = false;
    }

    /**
     * Check if the ball was lost or hit the player, wall, or borders, and react accordingly.
     */
    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            wallHandler.breakBrick();
            score++;
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < area.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }
    }

    public void startGame() {
        gameTimer.start();
    }

    public void stopGame() {
        gameTimer.stop();
    }

    public boolean isBallLost() {
        return ballLost;
    }

    /**
     * Reset the ball's and the player's positions to the starting point
     * and set the ball's speed to a new random speed.
     *
     * @see #restart()
     */
    public void ballAndPlayerPositionReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        ball.setSpeed(randomSpeedX(), randomSpeedY());
        ballLost = false;
    }

    /**
     * Set the score to 0 and tell {@link #wallHandler} to reset the walls.
     */
    public void wallReset() {
        wallHandler.wallReset();
        score = 0;
    }

    /**
     * Tells {@link #wallHandler} to move to the next level.
     */
    public void nextLevel() {
        wallHandler.nextLevel();
        score = 0;
    }

    /**
     * Restart the current level by resetting the
     * wall, score, and positions of the ball and player.
     *
     * @see #wallReset()
     * @see #ballAndPlayerPositionReset()
     */
    public void restart() {
        wallReset();
        ballAndPlayerPositionReset();
    }

    /**
     * Stop the player from moving
     */
    public void stopPlayer() {
        player.stop();
    }

    public void moveRight() {
        player.moveRight();
    }

    public void moveLeft() {
        player.moveLeft();
    }

    /**
     * Reset the ball count.
     */
    public void resetBallCount() {
        ballCount = 3;
    }

    /**
     * @return If the balls ran out (i.e. {@link #ballCount} == 0), then return true, otherwise return false.
     */
    public boolean ballEnd() {
        return ballCount == 0;
    }

    /**
     * Tell {@link #scoreHandler} to save the scores to storage
     */
    public void saveScores() {
        scoreHandler.saveScores();
    }

    /**
     * @return If there is a level left, return true, otherwise return false.
     */
    public boolean hasLevel() {
        return wallHandler.hasLevel();
    }

    public boolean isPaused() {
        return isPaused;
    }

    /**
     * @return If the game is running, return true, otherwise return false.
     */
    public boolean isRunning() {
        return gameTimer.isRunning();
    }

    /**
     * @return Ask {@link #wallHandler} if the current level is done, if it is, return true, otherwise return false.
     */
    public boolean isDone() {
        return wallHandler.isDone();
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public Brick[] getBricks() {
        return wallHandler.getBricks();
    }

    public int getBrickCount() {
        return wallHandler.getBrickCount();
    }

    public int getSpeedX() {
        return ball.getSpeedX();
    }

    public int getSpeedY() {
        return ball.getSpeedY();
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallSpeedX(int s) {
        ball.setSpeedX(s);
    }

    public void setBallSpeedY(int s) {
        ball.setSpeedY(s);
    }

    private int randomSpeedX() {
        int speedX;
        do {
            speedX = rnd.nextInt(3, 7) - 2;
        } while (speedX == 0);
        return speedX;
    }

    private int randomSpeedY() {
        int speedY;
        do {
            speedY = -rnd.nextInt(4, 6);
        } while (speedY == 0);
        return speedY;
    }

    private boolean impactWall() {
        for (Brick brick : getBricks()) {
            if (brick != null && !brick.isBroken() && brick.findImpact(ball) != null) {
                switch (brick.findImpact(ball)) {
                    //Vertical BrickImpact
                    case UP:
                        ball.reverseY();
                        return brick.setImpact(ball.getDown(), CrackType.UP);
                    case DOWN:
                        ball.reverseY();
                        return brick.setImpact(ball.getUp(), CrackType.DOWN);

                    //Horizontal BrickImpact
                    case LEFT:
                        ball.reverseX();
                        return brick.setImpact(ball.getRight(), CrackType.RIGHT);
                    case RIGHT:
                        ball.reverseX();
                        return brick.setImpact(ball.getLeft(), CrackType.LEFT);
                }
            }
        }
        return false;
    }

    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    private void initializeTimer() {
        String message = "Bricks: " + getBrickCount() +
                "   |   Balls left: " + getBallCount();
        Color color = Color.DARKBLUE;

        this.move();
        this.findImpacts();
        if (isBallLost()) {
            if (ballEnd()) {
                wallReset();
                message = "Game Over";
                color = Color.DARKRED;
                resetBallCount();
            }
            ballAndPlayerPositionReset();
            gameTimer.stop();
        } else if (isDone()) {
            if (hasLevel()) {
                message = "Go to Next Level";
                color = Color.MEDIUMAQUAMARINE;
                gameTimer.stop();
                ballAndPlayerPositionReset();
                nextLevel();
                wallReset();
            } else {
                message = "ALL WALLS DESTROYED";
                color = Color.DARKGREEN;
                gameTimer.stop();
            }
        }

        if (scoreHandler.checkAndSetHighscore(wallHandler.currentLevel(), score)) {
            controller.displayNewHighScore(score);
        }
        controller.updateStatus(message, color);
        controller.repaintView();
    }
}
