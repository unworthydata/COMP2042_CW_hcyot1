package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.BrickType;
import com.game.comp2042_cw_hcyot1.brick.Crack;
import com.game.comp2042_cw_hcyot1.game.ScoreHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    public int breakBrick() {
        return currentWall.breakBrick();
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

    public void wallReset() {
        currentWall.wallReset();
    }

    public boolean isDone() {
        return currentWall.isDone();
    }

    public void nextLevel() {
        if (wall + 1 < walls.length) {
            currentWall = walls[++wall];
            currentWall.resetBrickCount();
        }
    }

    public boolean hasLevel() {
        return wall < walls.length;
    }

    public Brick[] getBricks() {
        return currentWall.getBricks();
    }

    public int currentLevel() {
        return wall + 1;
    }
}
