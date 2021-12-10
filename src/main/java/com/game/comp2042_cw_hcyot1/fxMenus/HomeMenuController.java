package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.event.ActionEvent;


import java.io.IOException;

public class HomeMenuController {

    public void startGame(ActionEvent event) {
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void showInfo(ActionEvent event) {
        try {
            Controller.switchScenes("Info", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
