package com.game.comp2042_cw_hcyot1.painter;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;

import java.awt.*;

/**
 * Interface for Painter classes. Contains helper methods to draw objects of type {@link Shape}
 *
 * @see BasicPainter
 */
public interface Painter {
    void clear(Color bgColor);

    void draw(Shape shape, Color innerColor, Color borderColor);

    void drawBrick(Brick brick);

    void drawBall(Ball ball);

    void drawPlayer(Player p);
}
