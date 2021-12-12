package com.game.comp2042_cw_hcyot1.painter;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;

import java.awt.*;

/**
 * This class contains many simple drawing methods used by {@link com.game.comp2042_cw_hcyot1.game.GameView}.
 * It is an implementation  of the interface {@link Painter}.
 *
 * @see Painter
 */
public class BasicPainter implements Painter {
    private Graphics2D graphics2D;
    private int width;
    private int height;

    public BasicPainter(Graphics2D graphics2D, int width, int height) {
        this.graphics2D = graphics2D;
        this.width = width;
        this.height = height;
    }

    /**
     * Clear the current window by filling it with the background color.
     * @param bgColor the background color to fill the window with.
     */
    public void clear(Color bgColor) {
        Color tmp = graphics2D.getColor();
        graphics2D.setColor(bgColor);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setColor(tmp);
    }

    /**
     * Method to draw a {@link Shape}, given the shape, inner color, and border color.
     * @param shape {@link Shape} to be drawn.
     * @param innerColor Color to fill the shape with
     * @param borderColor Color of the shape's border
     */
    public void draw(Shape shape, Color innerColor, Color borderColor) {
        Color tmp = graphics2D.getColor();

        graphics2D.setColor(innerColor);
        graphics2D.fill(shape);

        graphics2D.setColor(borderColor);
        graphics2D.draw(shape);

        graphics2D.setColor(tmp);
    }

    /**
     * Method to draw a brick. Uses the brick's inner and border colors.
     * @param brick The {@link Brick} to be drawn.
     */
    public void drawBrick(Brick brick) {
        draw(brick.getBrick(), brick.getInnerColor(), brick.getBorderColor());
    }

    /**
     * Method to draw a ball. Uses the ball's inner and border colors.
     * @param ball The {@link Ball} to be drawn
     */
    public void drawBall(Ball ball) {
        draw(ball.getBallFace(), ball.getInnerColor(), ball.getBorderColor());
    }

    /**
     * Method to draw a player. Uses the player's inner and border colors.
     * @param player The {@link Player} to be drawn.
     */
    public void drawPlayer(Player player) {
        draw(player.getPlayerFace(), player.getInnerColor(), player.getBorderColor());
    }
}
