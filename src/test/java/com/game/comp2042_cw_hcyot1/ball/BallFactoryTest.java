package com.game.comp2042_cw_hcyot1.ball;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BallFactoryTest {
    //make sure correct ball type returned
    //assert throws exception otherwise

    @Test
    public void BallFactoryRainbowBall() {
        assertTrue(BallFactory.makeBall(new Point(), BallType.RAINBOW) instanceof RainbowBall);
    }

    @Test
    public void BallFactoryRubberBall() {
        assertTrue(BallFactory.makeBall(new Point(), BallType.RUBBER) instanceof RubberBall);
    }

    @Test
    public void BallFactoryNonExistentTypeTest() {
        assertThrows(IllegalArgumentException.class, ()-> BallFactory.makeBall(new Point(), BallType.valueOf("S")));
    }
}