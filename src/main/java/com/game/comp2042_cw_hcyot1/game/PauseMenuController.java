package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Controller;
import com.game.comp2042_cw_hcyot1.game.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenuController {
    private GameController controller;

    public PauseMenuController(GameController controller) {
        this.controller = controller;
    }

    public void resume(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.start(stage);
    }

    public void restart(ActionEvent event) throws IOException {
        controller.restart();
        resume(event);
    }

    public void exitToMainMenu(ActionEvent event) {
        Controller.switchScenes("mainMenu/MainMenu", event);
    }

    //    private void handleMouseClicked(MouseEvent mouseEvent) {
//        Point p = mouseEvent.getPoint();
//        if (!showPauseMenu)
//            return;
//        if (continueButtonRect.contains(p)) {
//            showPauseMenu = false;
//            repaint();
//        } else if (restartButtonRect.contains(p)) {
//            message = "Restarting Game...";
//            gameModel.ballReset();
//            gameModel.wallReset();
//            showPauseMenu = false;
//            repaint();
//        } else if (exitButtonRect.contains(p)) {
//            System.exit(0);
//        }
//    }
}
