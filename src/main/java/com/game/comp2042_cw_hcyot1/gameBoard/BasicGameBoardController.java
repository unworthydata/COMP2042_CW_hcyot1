package com.game.comp2042_cw_hcyot1.gameBoard;

import com.game.comp2042_cw_hcyot1.frame.GenericFrame;

public class BasicGameBoardController extends GameBoardController {
    @Override
    public void handleOnLostFocus() {
        model.stopGame();
        model.setMessage("Focus Lost");
        repaint();
    }
}
