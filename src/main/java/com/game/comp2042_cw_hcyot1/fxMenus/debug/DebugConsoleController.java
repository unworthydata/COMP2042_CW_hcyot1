package com.game.comp2042_cw_hcyot1.fxMenus.debug;

import com.game.comp2042_cw_hcyot1.fxMenus.game.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class DebugConsoleController {
    @FXML
    Slider xSlider;
    @FXML
    Slider ySlider;
    @FXML
    Label xSliderValue;
    @FXML
    Label ySliderValue;

    private GameModel gameModel;

    public void skipLevel(ActionEvent event) {
        gameModel.nextLevel();
    }

    public void resetBalls(ActionEvent event) {
        gameModel.resetBallCount();
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
