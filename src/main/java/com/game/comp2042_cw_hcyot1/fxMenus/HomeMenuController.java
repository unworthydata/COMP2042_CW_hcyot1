package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeMenuController extends Application {
    private static Scene scene;

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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(Controller.loadFXML("HomeMenu"),600,450);
        stage.setTitle("Brick Destroy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
