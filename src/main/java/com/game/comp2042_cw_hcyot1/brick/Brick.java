package com.game.comp2042_cw_hcyot1.brick;

import com.game.comp2042_cw_hcyot1.ball.Ball;

import java.awt.*;
import java.awt.geom.Point2D;

abstract public class Brick {
    protected Shape brickFace;

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

    /**
     * A method called when a ball hits a brick
     * @param point point of impact
     * @param direction direction of the hit
     * @return If the brick is broken, returns true, otherwise it returns false
     */
    public boolean setImpact(Point2D point, CrackType direction) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    /**
     * Used to find this brick's impact on the direction of motion of a ball
     * @param ball The ball that hit this brick
     * @return a enum entry showing what the brick's impact was on the ball's motion
     */
    public final BrickImpact findImpact(Ball ball) {
        if (broken)
            return null;

        if (brickFace.contains(ball.getRight()))
            return BrickImpact.LEFT;
        else if (brickFace.contains(ball.getLeft()))
            return BrickImpact.RIGHT;
        else if (brickFace.contains(ball.getUp()))
            return BrickImpact.DOWN;
        else if (brickFace.contains(ball.getDown()))
            return BrickImpact.UP;
        else
            return null;
    }

    /**
     * Reset the strength of a ball to its original full strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Used when a ball impacts this brick. Decrement strength by 1 point and check if the brick is broken.
     */
    public void impact() {
        strength--;
        broken = (strength == 0);
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

    public final boolean isBroken() {
        return broken;
    }

    private Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }
}





