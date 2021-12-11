package com.game.comp2042_cw_hcyot1.painter;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;

import java.awt.*;

public interface Painter {
    void clear(Color bgColor);

    void drawBrick(Brick brick);

    void drawBall(Ball ball);

    void drawPlayer(Player p);

    void obscureGameBoard();

    void draw(Shape shape, Color innerColor, Color borderColor);
}
