package com.game.comp2042_cw_hcyot1.mainMenu;

import com.game.comp2042_cw_hcyot1.Controller;
import javafx.event.ActionEvent;

public class InfoController {
    public void goBack(ActionEvent event) {
        Controller.switchScenes("mainMenu/MainMenu", event);
    }
}
