package com.game.comp2042_cw_hcyot1.gameBoard;

import com.game.comp2042_cw_hcyot1.Player;
import com.game.comp2042_cw_hcyot1.ball.Ball;
import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.debug.DebugConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

public abstract class GameBoardView extends JComponent implements
        KeyListener, MouseListener, MouseMotionListener {

    protected int strLen = 0;

    protected String CONTINUE = "Continue";
    protected String RESTART = "Restart";
    protected String EXIT = "Exit";
    protected String PAUSE = "Pause Menu";

    protected GameBoardController controller;
    protected Graphics2D g2d;
    protected Rectangle continueButtonRect;
    protected Rectangle exitButtonRect;
    protected Rectangle restartButtonRect;
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        clear();

        g2d.setColor(Color.BLUE);
        g2d.drawString(controller.getMessage(), 250, 225);

        drawBall(controller.getBall());

        for (Brick brick : controller.getBricks())
            if (!brick.isBroken())
                drawBrick(brick);

        drawPlayer(controller.getPlayer());

        if (controller.isPaused())
            drawMenu();

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        controller.handleKeyPressed(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        controller.handleKeyReleased(keyEvent);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!controller.isPaused())
            return;

        if (continueButtonRect.contains(p))
            controller.continueGame();
        else if (restartButtonRect.contains(p))
            controller.restartGame();
        else if (exitButtonRect.contains(p))
            controller.exitGame();
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
        if (exitButtonRect != null && controller.isPaused()) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus() {
        controller.handleOnLostFocus();
    }

    public void setController(GameBoardController controller) {
        this.controller = controller;
    }

    protected void initialize() {
        this.setPreferredSize(new Dimension(controller.getWidth(), controller.getHeight()));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    protected void clear() {
        Color tmp = g2d.getColor();
        g2d.setColor(controller.getBackgroundColor());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    protected void drawBrick(Brick brick) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());

        g2d.setColor(tmp);
    }

    protected void drawBall(Ball ball) {
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    protected void drawPlayer(Player p) {
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    protected void drawMenu() {
        obscureGameBoard();
        drawPauseMenu();
    }

    protected void obscureGameBoard() {
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, controller.getWidth(), controller.getHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    protected void drawPauseMenu() {
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(controller.getMenuFont());
        g2d.setColor(controller.getMenuColor());

        if (strLen == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = controller.getMenuFont().getStringBounds(PAUSE, frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE, x, y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        if (continueButtonRect == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = controller.getMenuFont().getStringBounds(CONTINUE, frc).getBounds();
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
