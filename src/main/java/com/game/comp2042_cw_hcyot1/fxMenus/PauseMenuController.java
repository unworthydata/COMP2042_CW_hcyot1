package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class PauseMenuController {
    private GameController controller;

    public void resume(ActionEvent event) {
        Stage stage = new Stage();
        stage.setScene(controller.getScene());
        stage.show();
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


    public void setGameController(GameController controller) {
        this.controller = controller;
    }
}
