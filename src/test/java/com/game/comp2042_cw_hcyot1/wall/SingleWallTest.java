package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.BrickType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleWallTest {
    private final double DRAW_AREA_WIDTH = 600.0;
    private Wall single;

    @Test
    void makeBricksClayTest() {
        single = new SingleWall(DRAW_AREA_WIDTH, BrickType.CLAY);
        assertNotNull(single.getBricks());
    }

    @Test
    void makeBricksCementTest() {
        single = new SingleWall(DRAW_AREA_WIDTH, BrickType.CEMENT);
        assertNotNull(single.getBricks());
    }

    @Test
    void makeBricksSteelTest() {
        single = new SingleWall(DRAW_AREA_WIDTH, BrickType.STEEL);
        assertNotNull(single.getBricks());
    }
}