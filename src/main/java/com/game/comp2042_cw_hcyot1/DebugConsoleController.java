package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Debug Console. You can skip levels, reset balls,
 * and change X and Y ball speed in the Debug Console.
 */
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

    /**
     * Initialize the Debug Console by setting change listeners to
     * the X and Y speed sliders, and updating the speed labels to reflect
     * the current speed.
     */
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

    /**
     * Tells the game model to skip to the next level.
     */
    public void skipLevel(ActionEvent event) {
        gameModel.nextLevel();
    }

    /**
     * Tells the game model to reset the number of balls remaining.
     */
    public void resetBalls(ActionEvent event) {
        gameModel.resetBallCount();
    }
}
