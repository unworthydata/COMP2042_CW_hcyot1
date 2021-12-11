package com.game.comp2042_cw_hcyot1.painter;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import javafx.scene.image.Image;

import java.awt.*;

public class BasicPainter implements Painter {
    private Graphics2D graphics2D;
    private int width;
    private int height;

    public BasicPainter(Graphics2D graphics2D, int width, int height) {
        this.graphics2D = graphics2D;
        this.width = width;
        this.height = height;
    }

    public void clear(Color bgColor) {
        Color tmp = graphics2D.getColor();
        graphics2D.setColor(bgColor);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setColor(tmp);
    }

    public void drawBrick(Brick brick) {
        draw(brick.getBrick(), brick.getInnerColor(), brick.getBorderColor());
    }

    public void drawBall(Ball ball) {
        draw(ball.getBallFace(), ball.getInnerColor(), ball.getBorderColor());
    }

    public void drawPlayer(Player p) {
        draw(p.getPlayerFace(), Player.INNER_COLOR, Player.BORDER_COLOR);
    }

    public void obscureGameBoard() {
        Composite tmp = graphics2D.getComposite();
        Color tmpColor = graphics2D.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        graphics2D.setComposite(ac);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, width, height);

        graphics2D.setComposite(tmp);
        graphics2D.setColor(tmpColor);
    }

    public void draw(Shape shape, Color innerColor, Color borderColor) {
        Color tmp = graphics2D.getColor();

        graphics2D.setColor(innerColor);
        graphics2D.fill(shape);

        graphics2D.setColor(borderColor);
        graphics2D.draw(shape);

        graphics2D.setColor(tmp);
    }
}
