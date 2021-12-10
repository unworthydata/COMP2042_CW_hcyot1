package com.game.comp2042_cw_hcyot1.fxMenus;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.debug.DebugConsole;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

public class GameView extends JComponent {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private BasicPainter painter;

    private com.game.comp2042_cw_hcyot1.fxMenus.GameModel gameModel;

    public GameView(GameModel gameModel) {
        super();
        this.initialize();

        this.gameModel = gameModel;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        painter = new BasicPainter(g2d, DEF_WIDTH, DEF_HEIGHT);
        painter.clear(BG_COLOR);

        painter.drawBall(gameModel.getBall());

        for (Brick brick : gameModel.getBricks())
            if (!brick.isBroken())
                painter.drawBrick(brick);

        painter.drawPlayer(gameModel.getPlayer());

        Toolkit.getDefaultToolkit().sync();
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void repaintView() {
        repaint();
    }
}
