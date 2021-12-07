package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;

public class BrickFactory {
    public static Brick makeBrick(Point point, Dimension size, BrickType type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%s\n", type));
        };
    }
}
