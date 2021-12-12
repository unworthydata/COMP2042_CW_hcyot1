package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.game.ScoreHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

/**
 * Controller for the leaderboard page.
 */
public class LeaderboardController implements Initializable {
    @FXML
    private ListView<String> highscoreListView;

    /**
     * Displays the list of high scores from the
     * file in storage containing the high scores.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LinkedHashMap<Integer, Integer> highscores = ScoreHandler.loadScores();
        for (var entry : highscores.entrySet()) {
            highscoreListView.getItems().add(
                    String.format("Level: %d      |      Highscore: %d", entry.getKey(), entry.getValue())
            );
        }
    }

    /**
     * Switches scenes to go back to the main menu.
     */
    public void goBack(ActionEvent event) {
        Controller.switchScenes("MainMenu", event);
    }
}
