package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class CementBrick extends Brick {
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private static final int DEF_CRACK_DEPTH = 1;
    private static final int DEF_STEPS = 35;
    private Crack crack = new Crack(DEF_CRACK_DEPTH, DEF_STEPS);

    public CementBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        super.impact();
        if (!super.isBroken()) {
            crack.makeCrack(point, dir, brickFace.getBounds());
            updateBrick();
            return false;
        }
        return true;
    }

    @Override
    public void repair() {
        super.repair();
        crack.reset();
    }

    private void updateBrick() {
        if (!isBroken()) {
            GeneralPath gp = crack.draw();
            gp.append(brickFace, false);
            brickFace = gp;
        }
    }
}
