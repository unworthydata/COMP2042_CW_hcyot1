package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.ScoreHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private ListView<String> highscoreListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LinkedHashMap<Integer, Integer> highscores = ScoreHandler.loadScores();
        for (var entry : highscores.entrySet()) {
            highscoreListView.getItems().add(
                    String.format("Level: %d      |      Highscore: %d", entry.getKey(), entry.getValue())
            );
        }
    }

    public void goBack(ActionEvent event) {
        Controller.switchScenes("MainMenu", event);
    }
}
