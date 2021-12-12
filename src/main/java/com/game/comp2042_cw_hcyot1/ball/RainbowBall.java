package com.game.comp2042_cw_hcyot1.ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class RainbowBall extends Ball {
    private static final int DEF_RADIUS = 15;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    private Random rnd = new Random();

    public RainbowBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }

    @Override
    public void reverseY() {
        speedY *= -1;
        innerColor = new java.awt.Color(rnd.nextInt(256), rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    protected Shape makeBall(Point2D center, int radius) {
        double x = center.getX() - (radius / 2);
        double y = center.getY() - (radius / 2);

        return new Ellipse2D.Double(x, y, radius, radius);
    }
}
