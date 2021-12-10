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
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.debug.DebugConsole;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener {
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0, 255, 0);

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private BasicPainter painter;

    private Timer gameTimer;

    private GameModel gameModel;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    public GameBoard(JFrame owner) {
        super();
        strLen = 0;
        showPauseMenu = false;

        menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);

        this.initialize();
        message = "";
        gameModel = new GameModel(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), new Point(300, 430));

        debugConsole = new DebugConsole(owner, gameModel, this);

        gameTimer = new Timer(10, e -> initializeTimer());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        painter = new BasicPainter(g2d, DEF_WIDTH, DEF_HEIGHT);
        painter.clear(BG_COLOR);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message, 250, 225);

        painter.drawBall(gameModel.getBall());

        for (Brick brick : gameModel.getBricks())
            if (!brick.isBroken())
                painter.drawBrick(brick);

        painter.drawPlayer(gameModel.getPlayer());

        if (showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                gameModel.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                gameModel.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if (!showPauseMenu)
                    if (gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                gameModel.getPlayer().stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameModel.getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!showPauseMenu)
            return;
        if (continueButtonRect.contains(p)) {
            showPauseMenu = false;
            repaint();
        } else if (restartButtonRect.contains(p)) {
            message = "Restarting Game...";
            gameModel.ballReset();
            gameModel.wallReset();
            showPauseMenu = false;
            repaint();
        } else if (exitButtonRect.contains(p)) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void initializeTimer() {
        gameModel.move();
        gameModel.findImpacts();
        message = String.format("Bricks: %d Balls %d", gameModel.getBrickCount(), gameModel.getBallCount());
        if (gameModel.isBallLost()) {
            if (gameModel.ballEnd()) {
                gameModel.wallReset();
                message = "Game over";
            }
            gameModel.ballReset();
            gameTimer.stop();
        } else if (gameModel.isDone()) {
            if (gameModel.hasLevel()) {
                message = "Go to Next Level";
                gameTimer.stop();
                gameModel.ballReset();
                gameModel.wallReset();
                gameModel.nextLevel();
            } else {
                message = "ALL WALLS DESTROYED";
                gameTimer.stop();
            }
        }

        repaint();
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void onLostFocus() {
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

    private void drawMenu(Graphics2D g2d) {
        painter.obscureGameBoard();
        drawPauseMenu(g2d);
    }

    private void drawPauseMenu(Graphics2D g2d) {
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if (strLen == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE, frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE, x, y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        if (continueButtonRect == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE, frc).getBounds();
            continueButtonRect.setLocation(x, y - continueButtonRect.height);
        }

        g2d.drawString(CONTINUE, x, y);

        y *= 2;

        if (restartButtonRect == null) {
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x, y - restartButtonRect.height);
        }

        g2d.drawString(RESTART, x, y);

        y *= 3.0 / 2;

        if (exitButtonRect == null) {
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x, y - exitButtonRect.height);
        }

        g2d.drawString(EXIT, x, y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}
