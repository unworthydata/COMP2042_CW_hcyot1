package com.game.comp2042_cw_hcyot1;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class containing utility fields and methods useful in Controller classes.
 */
public abstract class Controller {
    public static final int DEF_WIDTH = 600;
    public static final int DEF_HEIGHT = 450;

    /**
     * Load an FXML document and get a root node.
     * @param name Name of the FXML file without the extension
     * @return Root node
     * @throws IOException
     */
    public static Parent loadFXML(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(name + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Switch to a new scene given an {@link Event}.
     * The {@link Event} is needed to get the current stage so the program can stay in the same window.
     * @param dest Destination FXML file name without extension
     * @param event {@link Event} that caused this switch
     */
    public static void switchScenes (String dest, Event event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loadFXML(dest), DEF_WIDTH, DEF_HEIGHT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
