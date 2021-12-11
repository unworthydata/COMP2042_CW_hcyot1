package com.game.comp2042_cw_hcyot1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(Controller.loadFXML("mainMenu/MainMenu"), Controller.DEF_WIDTH, Controller.DEF_HEIGHT);

        stage.setTitle("Brick Destroy");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
