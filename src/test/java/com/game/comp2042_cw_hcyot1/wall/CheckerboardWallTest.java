package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.BrickType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerboardWallTest {
    private final double DRAW_AREA_WIDTH = 600.0;
    private Wall checkerboard;

    @Test
    void makeBricksClayCementTest() {
        checkerboard = new CheckerboardWall(DRAW_AREA_WIDTH, BrickType.CLAY, BrickType.CEMENT);
        assertNotNull(checkerboard.getBricks());
    }

    @Test
    void makeBricksClaySteelTest() {
        checkerboard = new CheckerboardWall(DRAW_AREA_WIDTH, BrickType.CLAY, BrickType.STEEL);
        assertNotNull(checkerboard.getBricks());
    }

    @Test
    void makeBricksSteelCementTest() {
        checkerboard = new CheckerboardWall(DRAW_AREA_WIDTH, BrickType.STEEL, BrickType.CEMENT);
        assertNotNull(checkerboard.getBricks());
    }
}