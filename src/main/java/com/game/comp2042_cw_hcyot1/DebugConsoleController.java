package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class DebugConsoleController implements Initializable {
    @FXML
    Slider xSlider;
    @FXML
    Slider ySlider;
    @FXML
    Label xSliderValue;
    @FXML
    Label ySliderValue;

    private GameModel gameModel;

    int currentXSpeed;
    int currentYSpeed;

    public DebugConsoleController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentXSpeed = Math.abs(gameModel.getSpeedX());
        xSlider.setValue(currentXSpeed);
        xSliderValue.setText(String.valueOf(currentXSpeed));

        currentYSpeed = Math.abs(gameModel.getSpeedY());
        ySlider.setValue(currentYSpeed);
        ySliderValue.setText(String.valueOf(currentYSpeed));

        xSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentXSpeed = newValue.intValue();
            xSliderValue.setText(String.valueOf(currentXSpeed));

            gameModel.setBallSpeedX(currentXSpeed);
        });

        ySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentYSpeed = newValue.intValue();
            ySliderValue.setText(String.valueOf(currentYSpeed));

            gameModel.setBallSpeedY(-currentYSpeed);
        });
    }

    public void skipLevel(ActionEvent event) {
        gameModel.nextLevel();
    }

    public void resetBalls(ActionEvent event) {
        gameModel.resetBallCount();
    }
}
