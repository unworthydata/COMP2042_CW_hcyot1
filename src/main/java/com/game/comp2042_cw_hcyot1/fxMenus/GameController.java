package com.game.comp2042_cw_hcyot1.fxMenus;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameController extends Application {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private Scene scene;
    private Stage stage;
    private GameModel gameModel = new GameModel(new Rectangle(0, 0, 600, 450), new Point(300, 430), this);
    private GameView gameView = new GameView(gameModel);

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        scene = new Scene(pane, DEF_WIDTH, DEF_HEIGHT);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);

        stage.setScene(scene);

        stage.focusedProperty().addListener(event -> onLostFocus());
        stage.show();

//        if (gameModel.isPaused())
//            pause();
    }

    public static void main() {
        launch();
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> swingNode.setContent(gameView));
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                gameModel.getPlayer().moveLeft();
                break;
            case D:
                gameModel.getPlayer().moveRight();
                break;
            case ESCAPE:
                if (gameModel.isPaused()) {
                    gameModel.unPauseGame();
                    gameModel.startGame();
                } else {
                    gameModel.pauseGame();
                    gameModel.stopGame();
                    pause();
                }
                break;
            case SPACE:
                if (!gameModel.isPaused())
                    if (gameModel.isRunning())
                        gameModel.stopGame();
                    else
                        gameModel.startGame();
                break;
            case F1:
                if (event.isAltDown() && event.isShiftDown())
                    Controller.showDebugConsole();
            default:
                gameModel.getPlayer().stop();
        }
    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        gameModel.stopPlayer();
    }

    public void onLostFocus() {
        gameModel.stopGame();
    }

    private void pause() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
            Parent root = loader.load();
            PauseMenuController pauseMenuController = loader.getController();
            pauseMenuController.setGameController(this);

            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public void repaintView() {
        gameView.repaintView();
    }
}
