package com.game.comp2042_cw_hcyot1.wall;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.brick.BrickFactory;
import com.game.comp2042_cw_hcyot1.brick.BrickType;

import java.awt.*;

public class HollowWall extends Wall {
    private BrickType type;

    public HollowWall(double drawAreaWidth, BrickType type) {
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

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

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
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            if (b) {
                tmp[i] = null;
                brickCount--;
            } else
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
