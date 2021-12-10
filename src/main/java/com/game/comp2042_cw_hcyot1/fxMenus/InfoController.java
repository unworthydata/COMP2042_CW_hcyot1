package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.io.IOException;

public class InfoController {
    public void goBack(ActionEvent event) {
        try {
            Controller.switchScenes("HomeMenu", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
