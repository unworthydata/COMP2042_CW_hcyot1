package com.game.comp2042_cw_hcyot1.fxMenus;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;
import com.game.comp2042_cw_hcyot1.painter.Painter;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class GameView {
    public void draw(FXGraphics2D g2d, GameModel gameModel) {
        Painter painter = new BasicPainter(g2d, 600, 450);
        painter.clear(Color.WHITE);

        painter.drawBall(gameModel.getBall());

        for (Brick brick : gameModel.getBricks())
            if (!brick.isBroken())
                painter.drawBrick(brick);

        painter.drawPlayer(gameModel.getPlayer());

        Toolkit.getDefaultToolkit().sync();
    }
}
