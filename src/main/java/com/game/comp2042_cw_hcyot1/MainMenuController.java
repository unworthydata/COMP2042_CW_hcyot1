package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Controller for the main menu.
 */
public class MainMenuController {

    /**
     * Starts the game in the same window.
     */
    public void startGame(ActionEvent event) {
        GameController game = new GameController();
        Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current.setResizable(false);
        game.start(current);
    }

    /**
     * Switch scenes to the info screen.
     */
    public void showInfo(ActionEvent event) {
        Controller.switchScenes("Info", event);
    }

    /**
     * Exits the game with error code 0 (no error).
     */
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Switch scenes to display the leaderboard screen.
     */
    public void showLeaderboard(ActionEvent event) {
        Controller.switchScenes("Leaderboard", event);
    }
}
