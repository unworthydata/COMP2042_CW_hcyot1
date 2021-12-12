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

    public void move() {
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX), (center.getY() + speedY));
        double width = tmp.getWidth();
        double height = tmp.getHeight();

        tmp.setFrame((center.getX() - (width / 2)), (center.getY() - (height / 2)), width, height);
        setPoints(width, height);

        ballFace = tmp;
    }

    public void moveTo(Point p) {
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() - (w / 2)), (center.getY() - (h / 2)), w, h);
        ballFace = tmp;
    }

    public void reverseX() {
        speedX *= -1;
    }

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

    public void setSpeed(int x, int y) {
        setSpeedX(x);
        setSpeedY(y);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int s) {
        speedX = s;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int s) {
        speedY = s;
    }

    public void setInnerColor(Color color) {
        innerColor = color;
    }

    protected abstract Shape makeBall(Point2D center, int radius);

    private void setPoints(double width, double height) {
        up.setLocation(center.getX(), center.getY() - (height / 2));
        down.setLocation(center.getX(), center.getY() + (height / 2));

        left.setLocation(center.getX() - (width / 2), center.getY());
        right.setLocation(center.getX() + (width / 2), center.getY());
    }
}
