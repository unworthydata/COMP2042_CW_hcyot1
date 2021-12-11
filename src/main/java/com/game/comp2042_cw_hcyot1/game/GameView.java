package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.awt.*;

public class GameView extends JComponent {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final java.awt.Color BG_COLOR = java.awt.Color.WHITE;

    private BasicPainter painter;

    private GameModel gameModel;

    private Graphics2D graphics2D;

    private Label statusLabel;

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

    public void drawStatus(StackPane parent) {
        statusLabel = new Label("STATUS");
        statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        statusLabel.setFont(new Font("Consolas", 15));
        parent.getChildren().add(statusLabel);
    }

    public void updateStatus(String string) {
        Platform.runLater(() -> statusLabel.setText(string));
    }

    public void updateStatus(String string, Color color) {
        Platform.runLater(() -> {
            statusLabel.setText(string);
            statusLabel.setTextFill(color);
        });
    }
}
