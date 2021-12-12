package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;

/**
 * A factory class to instantiate new Brick objects
 */
public class BrickFactory {
    /**
     * This approach allows us to easily add new Brick types. All we need to do is create the new class,
     * add an entry to the enum {@link BrickType}, and add a line of code here. After this, changing one Brick
     * for another is as simple as changing the BrickType used when calling this method.
     *
     * @param point The top left corner of the new Brick
     * @param size Size of the new Brick
     * @param type {@link BrickType} of the new Brick
     * @return a new {@link Brick}
     */
    public static Brick makeBrick(Point point, Dimension size, BrickType type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case MOSS -> new MossBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%s\n", type));
        };
    }
}
