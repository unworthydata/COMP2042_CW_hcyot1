package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(Controller.loadFXML("HomeMenu"), 600, 450);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F1)
                if (event.isAltDown() && event.isShiftDown())
                    Controller.showDebugConsole();
        });

        stage.setTitle("Brick Destroy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
