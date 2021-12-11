package com.game.comp2042_cw_hcyot1.fxMenus;

import com.game.comp2042_cw_hcyot1.fxMenus.game.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


import java.io.IOException;

public class HomeMenuController {

    public void startGame(ActionEvent event) {
        try {
            GameController game = new GameController();
            Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
}
