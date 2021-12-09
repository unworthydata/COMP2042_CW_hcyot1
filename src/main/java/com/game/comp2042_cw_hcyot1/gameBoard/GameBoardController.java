package com.game.comp2042_cw_hcyot1.gameBoard;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.frame.GenericFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameBoardController {
    protected GameBoardView view;
    protected GameBoardModel model;

    protected GenericFrame owner;

    public GameBoardController() {
        initialize();
    }

    public void initialize() {
        view.initialize();
    }

    public void repaint() {
        view.repaint();
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        model.handleKeyPressed(keyEvent);
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        model.handleKeyReleased();
    }

    public void continueGame() {
        model.unPause();
        repaint();
    }

    public void restartGame() {
        model.setMessage("Restarting Game...");
        model.reset();
        model.unPause();
        repaint();
    }

    public void exitGame() {
        System.exit(0);
    }

    public int getWidth() {
        return model.getWidth();
    }

    public int getHeight() {
        return model.getHeight();
    }

    public void setView(GameBoardView view) {
        this.view = view;
    }

    public void setModel(GameBoardModel model) {
        this.model = model;
    }

    public abstract void handleOnLostFocus();

    public String getMessage() {
        return model.getMessage();
    }

    public Ball getBall() {
        return model.getBall();
    }

    public Brick[] getBricks() {
        return model.getBricks();
    }

    public Player getPlayer() {
        return model.getPlayer();
    }

    public boolean isPaused() {
        return model.isPaused();
    }

    public Color getBackgroundColor() {
        return model.getBackgroundColor();
    }

    public Font getMenuFont() {
        return model.getMenuFont();
    }

    public Color getMenuColor() {
        return model.getMenuColor();
    }

    public void showDebug() {
        owner.showDebug();
    }

    public void setOwner(GenericFrame owner) {
        this.owner = owner;
    }
}
