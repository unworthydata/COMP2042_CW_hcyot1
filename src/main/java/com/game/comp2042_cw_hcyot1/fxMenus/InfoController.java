package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.event.ActionEvent;

public class InfoController {
    public void goBack(ActionEvent event) {
        Controller.switchScenes("HomeMenu", event);
    }
}
