package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Used in certain Brick types to draw a crack on the brick.
 */
public class Crack {
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    private GeneralPath crack;

    private static Random rnd = new Random();

    private int crackDepth;
    private int steps;

    public Crack(int crackDepth, int steps) {
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    /**
     * The crack is initialized in the constructor
     * @return a {@link GeneralPath} representation of a crack
     */
    public GeneralPath draw() {
        return crack;
    }

    /**
     * Reset a crack
     */
    public void reset() {
        crack.reset();
    }

    /**
     * A method to make a crack in a {@link Brick}.
     * @param point The point at which the crack starts
     * @param direction Direction of the crack
     * @param bounds Bounds of the {@link Brick}
     *
     * @see CementBrick
     * @see MossBrick
     */
    public void makeCrack(Point2D point, CrackType direction, Rectangle bounds) {
        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();

        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, CrackType.VERTICAL);
                makeCrack(impact, tmp);
                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, CrackType.VERTICAL);
                makeCrack(impact, tmp);
                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, CrackType.HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, CrackType.HORIZONTAL);
                makeCrack(impact, tmp);
                break;
        }
    }

    private void makeCrack(Point start, Point end) {
        GeneralPath path = new GeneralPath();

        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {
            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);
        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {
        if (rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    private Point makeRandomPoint(Point from, Point to, CrackType direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }
}

