package com.game.comp2042_cw_hcyot1.gameBoard;

import com.game.comp2042_cw_hcyot1.GameModel;
import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameBoardModel {
    protected static final int TEXT_SIZE = 30;
    protected Color menuColor = new Color(0, 255, 0);

    protected static final int DEF_WIDTH = 600;
    protected static final int DEF_HEIGHT = 450;

    protected Color bgColor = Color.WHITE;

    protected Timer gameTimer;

    protected GameModel gameModel;

    protected String message;

    protected boolean isPaused;

    protected Font menuFont;

    protected GameBoardController controller;

    public GameBoardModel() {
        isPaused = false;

        menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);

        message = "";
        gameModel = new GameModel(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), new Point(300, 430));

        gameTimer = new Timer(10, e -> initializeTimer());
    }

    private void initializeTimer() {
        gameModel.move();
        gameModel.findImpacts();
        message = String.format("Bricks: %d Balls %d", gameModel.getBrickCount(), gameModel.getBallCount());
        if (gameModel.isBallLost()) {
            if (gameModel.ballEnd()) {
                gameModel.wallReset();
                message = "Game over";
            }
            gameModel.ballReset();
            gameTimer.stop();
        } else if (gameModel.isDone()) {
            if (gameModel.hasLevel()) {
                message = "Go to Next Level";
                gameTimer.stop();
                gameModel.ballReset();
                gameModel.wallReset();
                gameModel.nextLevel();
            } else {
                message = "ALL WALLS DESTROYED";
                gameTimer.stop();
            }
        }

        controller.repaint();
    }

    public String getMessage() {
        return message;
    }

    public Ball getBall() {
        return gameModel.getBall();
    }

    public Brick[] getBricks() {
        return gameModel.getBricks();
    }

    public Player getPlayer() {
        return gameModel.getPlayer();
    }

    public int getWidth() {
        return DEF_WIDTH;
    }

    public int getHeight() {
        return DEF_HEIGHT;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    public Font getMenuFont() {
        return menuFont;
    }

    public Color getMenuColor() {
        return menuColor;
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                gameModel.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                gameModel.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                isPaused = !isPaused;
                controller.repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if (!isPaused)
                    if (gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    controller.showDebug();
            default:
                gameModel.getPlayer().stop();
        }
    }

    public void handleKeyReleased() {
        gameModel.getPlayer().stop();
    }

    public boolean unPause() {
         return isPaused = false;
    }

    public String setMessage(String message) {
        return this.message = message;
    }

    public void reset() {
        gameModel.wallReset();
    }

    public void stopGame() {
        gameTimer.stop();
    }

    public void setController(GameBoardController controller) {
        this.controller = controller;
    }
}
