package com.game.comp2042_cw_hcyot1.ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Abstract class representing the most important aspects of a Ball,
 * leaving only the implementation of making a ball to the class that extends it.
 *
 * @author Omar Ismail
 * @see RubberBall
 */
abstract public class Ball {
    protected Shape ballFace;

    protected Point2D center;
    protected Point2D up = new Point2D.Double();
    protected Point2D down = new Point2D.Double();
    protected Point2D left = new Point2D.Double();
    protected Point2D right = new Point2D.Double();

    protected Color borderColor;
    protected Color innerColor;

    protected int speedX = 0;
    protected int speedY = 0;

    /**
     *
     * @param center Center of the new ball
     * @param radius Radius of the new ball
     * @param innerColor Inner color that will fill the new ball
     * @param borderColor Border color that will be on the outside of the ball
     */
    public Ball(Point2D center, int radius, Color innerColor, Color borderColor) {
        this.center = center;
        this.innerColor = innerColor;
        this.borderColor = borderColor;

        setPoints(radius, radius);

        ballFace = makeBall(center, radius);
    }

    /**
     * Moves the ball by updating its center point and shape frame locations
     */
    public void move() {
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX), (center.getY() + speedY));
        double width = tmp.getWidth();
        double height = tmp.getHeight();

        tmp.setFrame((center.getX() - (width / 2)), (center.getY() - (height / 2)), width, height);
        setPoints(width, height);

        ballFace = tmp;
    }

    /**
     * Move the ball to a specific Point
     *
     * @param p The specific Point the ball should be moved to
     */
    public void moveTo(Point p) {
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)), (center.getY() - (h / 2)), w, h);
        ballFace = tmp;
    }

    /**
     * Reverse the speed on the x-axis
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * Reverse the speed on the y-axis
     */
    public void reverseY() {
        speedY *= -1;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public Point2D getPosition() {
        return center;
    }

    public Shape getBallFace() {
        return ballFace;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeed(int speedX, int speedY) {
        setSpeedX(speedX);
        setSpeedY(speedY);
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    protected abstract Shape makeBall(Point2D center, int radius);

    private void setPoints(double width, double height) {
        up.setLocation(center.getX(), center.getY() - (height / 2));
        down.setLocation(center.getX(), center.getY() + (height / 2));

        left.setLocation(center.getX() - (width / 2), center.getY());
        right.setLocation(center.getX() + (width / 2), center.getY());
    }
}
