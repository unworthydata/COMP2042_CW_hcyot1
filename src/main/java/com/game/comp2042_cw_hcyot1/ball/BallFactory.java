package com.game.comp2042_cw_hcyot1.ball;

import java.awt.geom.Point2D;

public class BallFactory {
    public static Ball makeBall(Point2D position, BallType type) {
        return switch (type) {
            case RUBBER -> new RubberBall(position);
            case RAINBOW -> new RainbowBall(position);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%s\n", type));
        };
    }
}
