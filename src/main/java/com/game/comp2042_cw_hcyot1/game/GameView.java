package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Controller;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;

/**
 * Every part of this software uses JavaFX, except this class.
 *
 * {@link GameView} uses a {@link SwingNode} to display the game,
 * which allows it to display Swing graphics in a
 * JavaFX context, but otherwise fully uses JavaFX.
 *
 * @see GameModel
 * @see GameController
 */
public class GameView extends JComponent {
    private static final java.awt.Color BG_COLOR = java.awt.Color.WHITE;

    private BasicPainter painter;

    private GameModel gameModel;

    private Graphics2D graphics2D;

    private StackPane root = new StackPane();
    ImageView paused = new ImageView(new Image("file:src\\main\\resources\\com\\game\\comp2042_cw_hcyot1\\game\\pause.png"));

    private Label statusLabel;
    private Label highScoreStatus;

    /**
     * The {@link SwingNode} is set here to display the game.
     * @param gameModel current game model which is needed to
     *                 draw the game objects later on
     */
    public GameView(GameModel gameModel) {
        super();
        this.gameModel = gameModel;

        final SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(() -> swingNode.setContent(this));

        root.getChildren().add(swingNode);
        drawStatus();

        paused.setPreserveRatio(true);
        paused.setFitHeight(140);
    }

    /**
     * Override from the {@link JComponent} parent class. Displays all game objects on the screen.
     */
    @Override
    public void paint(Graphics g) {
        graphics2D = (Graphics2D) g;
        painter = new BasicPainter(graphics2D, Controller.DEF_WIDTH, Controller.DEF_HEIGHT);
        painter.clear(BG_COLOR);

        painter.drawBall(gameModel.getBall());

        for (Brick brick : gameModel.getBricks())
            if (brick != null && !brick.isBroken())
                painter.drawBrick(brick);

        painter.drawPlayer(gameModel.getPlayer());

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Updates the view.
     * @see GameController
     */
    public void repaintView() {
        repaint();
    }

    /**
     * Updates the status message
     * @param string The new status message
     * @param color Color of the new status message
     */
    public void updateStatus(String string, Color color) {
        Platform.runLater(() -> {
            statusLabel.setText(string);
            statusLabel.setTextFill(color);
        });
    }

    /**
     * Displays the new high score.
     * @param newHighScore the new high score.
     */
    public void displayNewHighScore(int newHighScore) {
        Platform.runLater(() -> highScoreStatus.setText("NEW HIGH SCORE: " + newHighScore));
    }

    /**
     * Displays an image when the game is paused.
     */
    public void displayPaused() {
        try {
            root.getChildren().add(paused);
        } catch (Exception e) {
        }
    }

    /**
     * Indicate that focus was lost from the game window.
     */
    public void onLostFocus() {
        updateStatus("LOST FOCUS", Color.RED);
    }

    /**
     * Removes the pause image when the game is resumed/unpaused.
     */
    public void displayUnpaused() {
        try {
            root.getChildren().remove(paused);
        } catch (Exception e) {
        }
    }

    public StackPane getRoot() {
        return root;
    }

    private void drawStatus() {
        statusLabel = new Label("");
        statusLabel.setFont(new Font("Consolas", 15));

        highScoreStatus = new Label("");
        highScoreStatus.setFont(new Font("Consolas", 20));
        highScoreStatus.setTextFill(Color.DARKGOLDENROD);
        highScoreStatus.setTranslateY(25);

        root.getChildren().add(statusLabel);
        root.getChildren().add(highScoreStatus);
    }
}
