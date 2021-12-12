package com.game.comp2042_cw_hcyot1.ball;

import java.awt.geom.Point2D;

/**
 * A factory class to instantiate new Ball objects.
 *
 * @see BallType
 * @see com.game.comp2042_cw_hcyot1.game.GameModel#makeBall(Point2D)
 */
public class BallFactory {
    /**
     * This approach allows us to
     *  easily add new Ball types. All we need to do is create the new class, add an entry to
     *  the enum {@link BallType}, and add a line of code here. After this, changing one Ball
     *  for another is as simple as changing the BallType used when calling this method.
     *
     * @param center Center point of the new Ball.
     * @param type {@link BallType} of the new Ball
     * @return A new Ball object
     */
    public static Ball makeBall(Point2D center, BallType type) {
        return switch (type) {
            case RUBBER -> new RubberBall(center);
            case RAINBOW -> new RainbowBall(center);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%s\n", type));
        };
    }
}
