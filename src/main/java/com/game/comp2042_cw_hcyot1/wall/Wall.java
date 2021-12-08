package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.Brick;

public abstract class Wall {
    protected final int INIT_BRICK_COUNT = 30;
    protected final int INIT_LINE_COUNT = 3;
    protected final double BRICK_DIMENSION_RATIO = 6 /2;

    protected int brickCount = INIT_BRICK_COUNT;
    protected int lineCount = INIT_LINE_COUNT;

    protected Brick[] bricks;

    protected double drawAreaWidth;

    public Wall(double drawAreaWidth) {
        this.drawAreaWidth = drawAreaWidth;
    }

    public int breakBrick() {
        return brickCount--;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public boolean isDone() {
        return brickCount == 0;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public int resetBrickCount() {
        return brickCount = bricks.length;
    }

    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
    }

    protected abstract Brick[] makeWall();
}
