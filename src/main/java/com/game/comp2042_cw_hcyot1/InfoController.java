package com.game.comp2042_cw_hcyot1;

import javafx.event.ActionEvent;

/**
 * Controller for the info page.
 */
public class InfoController {
    /**
     * Switches scene back to the Main Menu.
     */
    public void goBack(ActionEvent event) {
        Controller.switchScenes("MainMenu", event);
    }
}
