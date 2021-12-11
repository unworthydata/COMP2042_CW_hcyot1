package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.Controller;
import com.game.comp2042_cw_hcyot1.LeaderboardController;
import com.game.comp2042_cw_hcyot1.game.GameController;
import com.game.comp2042_cw_hcyot1.game.ScoreHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void startGame(ActionEvent event) {
        try {
            GameController game = new GameController();
            Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();
            current.setResizable(false);
            game.start(current);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
