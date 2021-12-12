package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.BrickType;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates new {@link Wall} instances and groups them together.
 * Understands the context of multiple walls together, and moves from one wall to the next.
 *
 * This class stands as an mediator between the {@link com.game.comp2042_cw_hcyot1.game.GameModel}
 * class and the {@link Wall} class.
 */
public class WallHandler {
    private Wall[] walls;
    private Wall currentWall;
    private double drawAreaWidth;

    private int wall = 0;

    public WallHandler(double drawAreaWidth) {
        this.drawAreaWidth = drawAreaWidth;
        walls = makeWalls();
        currentWall = walls[0];
    }

    /**
     * Tells the current wall to break a brick.
     * @return The number of bricks after breaking a brick.
     */
    public int breakBrick() {
        return currentWall.breakBrick();
    }

    /**
     * Tells the current wall to reset itself (repair any broken bricks and reset)
     */
    public void wallReset() {
        currentWall.wallReset();
    }

    /**
     * Move to the next wall.
     */
    public void nextLevel() {
        if (wall + 1 < walls.length)
            currentWall = walls[++wall];
    }

    /**
     * Checks if the current wall is the last wall
     * @return true if there are more walls after the current wall.
     */
    public boolean hasLevel() {
        return wall < walls.length;
    }

    /**
     * @return index of current wall (1-indexed, hence the +1)
     */
    public int currentLevel() {
        return wall + 1;
    }

    /**
     * Check if the current wall is done (all bricks are broken)
     * @return true if the current wall has no more bricks, otherwise return false
     */
    public boolean isDone() {
        return currentWall.isDone();
    }

    public Brick[] getBricks() {
        return currentWall.getBricks();
    }

    public int getBrickCount() {
        return currentWall.getBrickCount();
    }

    private Wall[] makeWalls() {
        List<Wall> wallList = new ArrayList<>();
        wallList.add(new HollowWall(drawAreaWidth, BrickType.MOSS));
        wallList.add(new SingleWall(drawAreaWidth, BrickType.MOSS));
        wallList.add(new SingleWall(drawAreaWidth, BrickType.CLAY));
        wallList.add(new CheckerboardWall(drawAreaWidth, BrickType.CLAY, BrickType.CEMENT));
        wallList.add(new CheckerboardWall(drawAreaWidth, BrickType.CLAY, BrickType.STEEL));
        wallList.add(new CheckerboardWall(drawAreaWidth, BrickType.STEEL, BrickType.CEMENT));

        return wallList.toArray(new Wall[0]);
    }
}
