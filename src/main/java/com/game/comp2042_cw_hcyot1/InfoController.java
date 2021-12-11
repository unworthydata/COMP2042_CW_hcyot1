package com.game.comp2042_cw_hcyot1;

import com.game.comp2042_cw_hcyot1.Controller;
import javafx.event.ActionEvent;

public class InfoController {
    public void goBack(ActionEvent event) {
        Controller.switchScenes("MainMenu", event);
    }
}
