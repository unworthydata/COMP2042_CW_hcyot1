package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.Brick;

/**
 * This class handles building walls and interacting with bricks.
 *
 * @see WallHandler
 */
public abstract class Wall {
    protected final int INIT_BRICK_COUNT = 30;
    protected final int INIT_LINE_COUNT = 3;
    protected final double BRICK_DIMENSION_RATIO = 6 / 2;

    protected int brickCount = INIT_BRICK_COUNT;
    protected int lineCount = INIT_LINE_COUNT;

    protected Brick[] bricks;

    protected double drawAreaWidth;

    public Wall(double drawAreaWidth) {
        this.drawAreaWidth = drawAreaWidth;
    }

    /**
     * Breaks a brick.
     * @return The new number of bricks.
     */
    public int breakBrick() {
        return --brickCount;
    }

    /**
     * If there are no bricks left, the level is done.
     * @return if all bricks are broken, return true, otherwise return false.
     */
    public boolean isDone() {
        return brickCount == 0;
    }

    /**
     * Reset the wall by repairing all its bricks and resetting its brick count.
     */
    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * @return {@link Brick} array containing bricks in the correct order to
     * produce the desired effect depending on the child class' implementation.
     */
    protected abstract Brick[] makeBricks();
}
