package com.game.comp2042_cw_hcyot1.frame;

import com.game.comp2042_cw_hcyot1.HomeMenu;
import com.game.comp2042_cw_hcyot1.debug.DebugConsole;
import com.game.comp2042_cw_hcyot1.gameBoard.*;
import com.game.comp2042_cw_hcyot1.mainMenu.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public abstract class GenericFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";
    protected final MainMenuView mainMenuView;

    protected GameBoardModel model;
    protected GameBoardView view;
    protected GameBoardController controller;

    private boolean gaming;

    public GenericFrame(GameBoardModel model, GameBoardView gameBoardView, GameBoardController controller, MainMenuView mainMenuView) {
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        this.model = model;
        this.view = gameBoardView;
        this.controller = controller;
        connect(model, gameBoardView, controller);

        this.mainMenuView = mainMenuView;
        this.add(mainMenuView, BorderLayout.CENTER);

        this.setUndecorated(true);
    }

    public void initialize() {
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableGameBoard() {
        this.dispose();
        this.remove(mainMenuView);
        this.add(view, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the genericFrame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming)
            controller.handleOnLostFocus();
    }

    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    private void connect(GameBoardModel model, GameBoardView view, GameBoardController controller) {
        view.setController(controller);

        controller.setModel(model);
        controller.setView(view);

        model.setController(controller);
    }

    public abstract void showDebug();
}
