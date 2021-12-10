package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DebugConsoleController extends Application {
    private Scene scene;
    @Override
    public void start(Stage stage) {
        try {
            scene = new Scene(Controller.loadFXML("DebugConsole"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.show();
    }
}
