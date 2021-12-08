/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.ball.RubberBall;
import com.game.comp2042_cw_hcyot1.brick.*;
import com.game.comp2042_cw_hcyot1.wall.WallHandler;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GameModel {
    private Random rnd;
    private Rectangle area;

    private Ball ball;
    private Player player;

    private Point startPoint;
    private int ballCount;
    private boolean ballLost;

    private WallHandler wallHandler;

    public GameModel(Rectangle drawArea, Point ballPos) {
        this.startPoint = new Point(ballPos);

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);

        ball.setSpeed(randomSpeedX(), randomSpeedY());

        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;

        wallHandler = new WallHandler(drawArea.getWidth());
    }

    private void makeBall(Point2D ballPos) {
        ball = new RubberBall(ballPos);
    }

    public void move() {
        player.move();
        ball.move();
    }

    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            wallHandler.breakBrick();
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < area.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        ball.setSpeed(randomSpeedX(), randomSpeedY());
        ballLost = false;
    }

    public void wallReset() {
        wallHandler.wallReset();
        ballReset();
    }

    public boolean ballEnd() {
        return ballCount == 0;
    }

    public void nextLevel() {
        wallHandler.nextLevel();
    }

    public void setBallSpeedX(int s) {
        ball.setSpeedX(s);
    }

    public void setBallSpeedY(int s) {
        ball.setSpeedY(s);
    }

    public void resetBallCount() {
        ballCount = 3;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    private int randomSpeedY() {
        int speedY;
        do {
            speedY = -rnd.nextInt(3);
        } while (speedY == 0);
        return speedY;
    }

    private int randomSpeedX() {
        int speedX;
        do {
            speedX = rnd.nextInt(5) - 2;
        } while (speedX == 0);
        return speedX;
    }

    private boolean impactWall() {
        return wallHandler.impactWall(ball);
    }

    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    public Brick[] getBricks() {
        return wallHandler.getBricks();
    }

    public boolean isDone() {
        return wallHandler.isDone();
    }

    public boolean hasLevel() {
        return wallHandler.hasLevel();
    }

    public int getBrickCount() {
        return wallHandler.getBrickCount();
    }
}
