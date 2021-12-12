package com.game.comp2042_cw_hcyot1.brick;

import com.game.comp2042_cw_hcyot1.ball.Ball;

import java.awt.*;
import java.awt.geom.Point2D;

abstract public class Brick {
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    Shape brickFace;

    private Color borderColor;
    private Color innerColor;

    private int fullStrength;
    private int strength;

    private boolean broken = false;

    public Brick(Point pos, Dimension size, Color borderColor, Color innerColor, int strength) {
        this.borderColor = borderColor;
        this.innerColor = innerColor;
        this.fullStrength = this.strength = strength;
        brickFace = makeBrickFace(pos, size);
    }

    public boolean setImpact(Point2D point, int direction) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public Shape getBrick() {
        return brickFace;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public final int findImpact(Ball ball) {
        if (broken)
            return 0;

        if (brickFace.contains(ball.getRight()))
            return LEFT_IMPACT;
        else if (brickFace.contains(ball.getLeft()))
            return RIGHT_IMPACT;
        else if (brickFace.contains(ball.getUp()))
            return DOWN_IMPACT;
        else if (brickFace.contains(ball.getDown()))
            return UP_IMPACT;
        else
            return 0;
    }

    public final boolean isBroken() {
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact() {
        strength--;
        broken = (strength == 0);
    }

    private Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }
}





