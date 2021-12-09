package com.game.comp2042_cw_hcyot1.frame;

import com.game.comp2042_cw_hcyot1.gameBoard.*;
import com.game.comp2042_cw_hcyot1.mainMenu.BasicMainMenuView;

import java.awt.*;

public class BasicFrame extends GenericFrame {
    public BasicFrame() {
        super(new BasicGameBoardModel(), new BasicGameBoardView(), new BasicGameBoardController(), new BasicMainMenuView(new Dimension(450, 300)));
        controller.setOwner(this);
        mainMenuView.setOwner(this);
    }

    @Override
    public void showDebug() {
//        show debug
    }
}
