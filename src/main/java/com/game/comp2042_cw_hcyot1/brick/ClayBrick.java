package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.Point;

/**
 * The most basic implementation of the {@link Brick} abstract class. Breaks in one hit.
 */
public class ClayBrick extends Brick {
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    public ClayBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
    }
}
