package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Controls the Pause Menu.
 */
public class PauseMenuController {
    private GameController controller;

    /**
     * The game controller is set here so that the {@link PauseMenuController}
     * can resume to the same game with the same state.
     * @param controller Current Game Controller
     */
    public PauseMenuController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Resume the game and hand control over to the current {@link GameController} (called {@link #controller})
     */
    public void resume(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.start(stage);
    }

    /**
     * Tells the current game controller to restart the game.
     */
    public void restart(ActionEvent event) {
        controller.restart();
        resume(event);
    }

    /**
     * Saves the high scores and hands over control to the main menu.
     */
    public void exitToMainMenu(ActionEvent event) {
        controller.saveScores();
        Controller.switchScenes("MainMenu", event);
    }
}
