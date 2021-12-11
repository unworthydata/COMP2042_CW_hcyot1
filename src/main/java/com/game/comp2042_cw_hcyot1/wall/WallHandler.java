package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.BrickType;
import com.game.comp2042_cw_hcyot1.brick.Crack;
import com.game.comp2042_cw_hcyot1.game.ScoreHandler;

import java.io.IOException;
import java.util.LinkedHashMap;

public class WallHandler {
    private final int WALLS_COUNT = 4;

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
        Wall[] tmp = new Wall[WALLS_COUNT];
        tmp[0] = new SingleWall(drawAreaWidth, BrickType.CLAY);
        tmp[1] = new CheckerboardWall(drawAreaWidth, BrickType.CLAY, BrickType.CEMENT);
        tmp[2] = new CheckerboardWall(drawAreaWidth, BrickType.CLAY, BrickType.STEEL);
        tmp[3] = new CheckerboardWall(drawAreaWidth, BrickType.STEEL, BrickType.CEMENT);
        return tmp;
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

    public boolean impactWall(Ball ball) {
        for (Brick brick : currentWall.getBricks()) {
            switch (brick.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return brick.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return brick.setImpact(ball.getUp(), Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return brick.setImpact(ball.getRight(), Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return brick.setImpact(ball.getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    public Brick[] getBricks() {
        return currentWall.getBricks();
    }

    public int currentLevel() {
        return wall + 1;
    }
}
