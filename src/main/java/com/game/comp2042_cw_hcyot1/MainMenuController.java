package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MainMenuController {

    public void startGame(ActionEvent event) {
        GameController game = new GameController();
        Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();
        current.setResizable(false);
        game.start(current);
    }

    public void showInfo(ActionEvent event) {
        Controller.switchScenes("Info", event);
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void showLeaderboard(ActionEvent event) {
        Controller.switchScenes("Leaderboard", event);
    }
}
