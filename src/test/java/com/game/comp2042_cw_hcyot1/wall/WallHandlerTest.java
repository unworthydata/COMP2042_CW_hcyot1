package com.game.comp2042_cw_hcyot1.wall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallHandlerTest {
    private final double DRAW_AREA_WIDTH = 600.0;
    private WallHandler wallHandler;

    @BeforeEach
    void setUp() {
        wallHandler = new WallHandler(DRAW_AREA_WIDTH);
    }

    @Test
    void breakBrick() {
        assertEquals(wallHandler.getBrickCount() - 1, wallHandler.breakBrick());
    }
}