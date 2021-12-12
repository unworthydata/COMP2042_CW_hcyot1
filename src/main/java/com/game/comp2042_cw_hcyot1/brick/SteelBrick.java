/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * An implementation of the Brick abstract class where a brick is not guaranteed to break when hit, rather,
 * there is a probability {@link #STEEL_PROBABILITY} that the steel brick breaks.
 *
 * @see Brick
 */
public class SteelBrick extends Brick {
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd = new Random();

    public SteelBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
    }

    /**
     * Randomization is used to determine if the brick was impacted or not.
     * Since this brick has a {@link #STEEL_STRENGTH} = 1,
     * this means that when a ball hits and is considered to have impacted, the brick breaks immediately.
     */
    @Override
    public void impact() {
        if (rnd.nextDouble() < STEEL_PROBABILITY)
            super.impact();
    }
}
