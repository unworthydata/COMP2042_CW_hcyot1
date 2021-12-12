package com.game.comp2042_cw_hcyot1.brick;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrickFactoryTest {

    @Test
    public void BrickFactoryClayBrick() {
        assertInstanceOf(ClayBrick.class, BrickFactory.makeBrick(new Point(),
                new Dimension(0, 0), BrickType.CLAY));
    }

    @Test
    public void BrickFactoryCementBrick() {
        assertInstanceOf(CementBrick.class, BrickFactory.makeBrick(new Point(),
                new Dimension(0, 0), BrickType.CEMENT));
    }

    @Test
    public void BrickFactorySteelBrick() {
        assertInstanceOf(SteelBrick.class, BrickFactory.makeBrick(new Point(),
                new Dimension(0, 0), BrickType.STEEL));
    }

    @Test
    public void BrickFactoryNonExistentBrickType() {
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.makeBrick(new Point(),
                        new Dimension(0, 0), BrickType.valueOf("G")));
    }
}