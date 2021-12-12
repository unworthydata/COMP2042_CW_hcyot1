package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.BrickFactory;
import com.game.comp2042_cw_hcyot1.brick.BrickType;

import java.awt.*;

/**
 * An implementation of the abstract class {@link Wall} that produces a wall of bricks of a single type.
 */
public class SingleWall extends Wall {
    private BrickType type;

    public SingleWall(double drawAreaWidth, BrickType type) {
        super(drawAreaWidth);
        this.type = type;

        bricks = makeBricks();
    }

    @Override
    protected Brick[] makeBricks() {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLen = drawAreaWidth / brickOnLine;
        double brickHgt = brickLen / BRICK_DIMENSION_RATIO;

        brickCount += lineCount / 2;

        Brick[] tmp = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = BrickFactory.makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = BrickFactory.makeBrick(p, brickSize, type);
        }

        return tmp;
    }
}
