package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;

public class GameView extends JComponent {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final java.awt.Color BG_COLOR = java.awt.Color.WHITE;

    private BasicPainter painter;

    private GameModel gameModel;

    private Graphics2D graphics2D;

    private Label statusLabel;
    private Label highScoreStatus;

    public GameView(GameModel gameModel) {
        super();
        this.initialize();
        this.gameModel = gameModel;
    }

    @Override
    public void paint(Graphics g) {
        graphics2D = (Graphics2D) g;
        painter = new BasicPainter(graphics2D, DEF_WIDTH, DEF_HEIGHT);
        painter.clear(BG_COLOR);

        painter.drawBall(gameModel.getBall());

        for (Brick brick : gameModel.getBricks())
            if (!brick.isBroken())
                painter.drawBrick(brick);

        painter.drawPlayer(gameModel.getPlayer());

        Toolkit.getDefaultToolkit().sync();
    }

    public void repaintView() {
        repaint();
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void drawStatus(StackPane root) {
        statusLabel = new Label("");
        statusLabel.setFont(new Font("Consolas", 15));

        highScoreStatus = new Label("");
        highScoreStatus.setFont(new Font("Consolas", 20));
        highScoreStatus.setTextFill(Color.DARKGOLDENROD);
        highScoreStatus.setTranslateY(25);

        root.getChildren().add(statusLabel);
        root.getChildren().add(highScoreStatus);
    }

    public void updateStatus(String string, Color color) {
        Platform.runLater(() -> {
            statusLabel.setText(string);
            statusLabel.setTextFill(color);
        });
    }

    public void displayNewHighScore(int newHighScore) {
        Platform.runLater(() -> highScoreStatus.setText("NEW HIGH SCORE: " + newHighScore));
    }

    public void displayPaused() {

    }
}
