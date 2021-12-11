package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.ball.RubberBall;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.wall.WallHandler;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GameModel {
    private Random rnd;
    private Rectangle area;

    private Ball ball;
    private Player player;

    private Point startPoint;
    private GameController controller;
    private int ballCount;
    private boolean ballLost;

    private WallHandler wallHandler;
    private Timer gameTimer;

    private boolean isPaused;

    public GameModel(Rectangle drawArea, Point ballPos, GameController controller) {
        this.startPoint = new Point(ballPos);
        this.controller = controller;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);

        ball.setSpeed(randomSpeedX(), randomSpeedY());

        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;

        wallHandler = new WallHandler(drawArea.getWidth());

        isPaused = false;

        gameTimer = new Timer(10, e -> initializeTimer());
    }

    public void move() {
        player.move();
        ball.move();
    }

    public void pauseGame() {
        isPaused = true;
        gameTimer.stop();
    }

    public void unPauseGame() {
        isPaused = false;
    }

    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            wallHandler.breakBrick();
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < area.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }
    }

    public void stopGame() {
        gameTimer.stop();
    }

    private void makeBall(Point2D ballPos) {
        ball = new RubberBall(ballPos);
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        ball.setSpeed(randomSpeedX(), randomSpeedY());
        ballLost = false;
    }

    public void wallReset() {
        wallHandler.wallReset();
        ballReset();
    }

    public boolean ballEnd() {
        return ballCount == 0;
    }

    public void nextLevel() {
        wallHandler.nextLevel();
    }

    public void setBallSpeedX(int s) {
        ball.setSpeedX(s);
    }

    public void setBallSpeedY(int s) {
        ball.setSpeedY(s);
    }

    public void resetBallCount() {
        ballCount = 3;
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

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isRunning() {
        return gameTimer.isRunning();
    }

    public void startGame() {
        gameTimer.start();
    }

    public boolean isDone() {
        return wallHandler.isDone();
    }

    public boolean hasLevel() {
        return wallHandler.hasLevel();
    }

    public int getBrickCount() {
        return wallHandler.getBrickCount();
    }

    public void stopPlayer() {
        player.stop();
    }

    public void moveRight() {
        player.moveRight();
    }

    public void moveLeft() {
        player.moveLeft();
    }

    public int getSpeedX() {
        return ball.getSpeedX();
    }

    public int getSpeedY() {
        return ball.getSpeedY();
    }

    public void restart() {
        wallReset();
        ballReset();
    }

    private int randomSpeedY() {
        int speedY;
        do {
            speedY = -rnd.nextInt(3);
        } while (speedY == 0);
        return speedY;
    }

    private int randomSpeedX() {
        int speedX;
        do {
            speedX = rnd.nextInt(5) - 2;
        } while (speedX == 0);
        return speedX;
    }

    private boolean impactWall() {
        return wallHandler.impactWall(ball);
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
            }
            ballReset();
            gameTimer.stop();
        } else if (isDone()) {
            if (hasLevel()) {
                message = "Go to Next Level";
                color = Color.MEDIUMAQUAMARINE;
                gameTimer.stop();
                ballReset();
                wallReset();
                nextLevel();
            } else {
                message = "ALL WALLS DESTROYED";
                color = Color.DARKGREEN;
                gameTimer.stop();
            }
        }

        controller.updateStatus(message, color);
        controller.repaintView();
    }
}
