package com.game.comp2042_cw_hcyot1.mainMenu;

import com.game.comp2042_cw_hcyot1.frame.GenericFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class MainMenuView extends JComponent implements MouseListener, MouseMotionListener {
    protected Graphics2D graphics2D;
    protected MainMenuController controller;

    protected GenericFrame owner;

    public MainMenuView(Dimension area) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setPreferredSize(area);
    }

    @Override
    public void paint(Graphics g) {
        graphics2D = (Graphics2D) g;
        drawMenu();
    }

    public void setController(MainMenuController controller) {
        this.controller = controller;
    }

    public void setOwner(GenericFrame owner) {
        this.owner = owner;
    }

    protected abstract void drawMenu();

    protected abstract void drawText();

    protected abstract void drawButton();
}
