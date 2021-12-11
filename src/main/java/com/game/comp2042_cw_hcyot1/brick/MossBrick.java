package com.game.comp2042_cw_hcyot1.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class MossBrick extends Brick {
    private static final Color DEF_INNER = new Color(96, 96, 96);
    private static final Color DEF_BORDER = new Color(128, 199, 50);
    private static final int MOSS_STRENGTH = 1;

    private static final int DEF_CRACK_DEPTH = 1;
    private static final int DEF_STEPS = 40;

    private Crack crack = new Crack(DEF_CRACK_DEPTH, DEF_STEPS);
    private Random random = new Random();

    public MossBrick(Point point, Dimension size) {
        super(point, size, DEF_BORDER, DEF_INNER, MOSS_STRENGTH);

        int xRand = random.nextInt(point.x, point.x + (int) size.getWidth());
        int yRand = random.nextInt(point.y, point.y + (int) size.getHeight());
        crack.makeCrack(new Point(xRand, yRand), Crack.RIGHT, brickFace.getBounds());
        crack.makeCrack(new Point(xRand, yRand), Crack.DOWN, brickFace.getBounds());

        GeneralPath gp = crack.draw();
        gp.append(brickFace, false);
        brickFace = gp;
    }
}
