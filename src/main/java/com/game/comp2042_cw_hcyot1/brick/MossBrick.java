package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;

/**
 * An implementation of the Brick abstract class. This implementation has a dark grey color with a green border
 * and green cracks running through the brick to imitate the look of a mossy brick. The {@link Crack} class is
 * used here to draw the cracks. These cracks do not have any meaning regarding the strength of the brick.
 */
public class MossBrick extends Brick {
    private static final Color DEF_INNER = new Color(96, 96, 96);
    private static final Color DEF_BORDER = new Color(128, 199, 50);
    private static final int MOSS_STRENGTH = 1;

    private static final int DEF_CRACK_DEPTH = 1;
    private static final int DEF_STEPS = 40;

    private Crack crack = new Crack(DEF_CRACK_DEPTH, DEF_STEPS);
    private Random random = new Random();

    /**
     * Randomised drawing of cracks
     * @param point Point at which the brick starts being drawn
     * @param size Size of the brick
     */
    public MossBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, MOSS_STRENGTH);

        int xRand = random.nextInt(point.x, point.x + (int) size.getWidth());
        int yRand = random.nextInt(point.y, point.y + (int) size.getHeight());
        // RIGHT and DOWN CrackTypes just looked right
        crack.makeCrack(new Point(xRand, yRand), CrackType.RIGHT, brickFace.getBounds());
        crack.makeCrack(new Point(xRand, yRand), CrackType.DOWN, brickFace.getBounds());

        // update the brickface
        GeneralPath gp = crack.draw();
        gp.append(brickFace, false);
        brickFace = gp;
    }
}
