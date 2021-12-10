package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public static Parent loadFXML(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(name + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchScenes (String dest, Event event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loadFXML(dest), 600, 450);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDebugConsole() {
        DebugConsoleController debugConsole = new DebugConsoleController();
        debugConsole.start(new Stage());
    }
}
