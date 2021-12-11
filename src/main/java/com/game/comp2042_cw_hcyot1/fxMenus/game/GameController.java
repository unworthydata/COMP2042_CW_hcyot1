package com.game.comp2042_cw_hcyot1.fxMenus.game;

import com.game.comp2042_cw_hcyot1.fxMenus.PauseMenuController;
import com.game.comp2042_cw_hcyot1.fxMenus.debug.DebugConsoleController;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class GameController extends Application {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private Scene scene;
    private Stage stage;

    private GameModel gameModel = new GameModel(new Rectangle(0, 0, 600, 450),
            new Point(300, 430), this);
    private GameView gameView = new GameView(gameModel);

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        gameView.drawStatus(pane);

        scene = new Scene(pane, DEF_WIDTH, DEF_HEIGHT);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);

        stage.setScene(scene);

        stage.focusedProperty().addListener(event -> {
            if (!stage.focusedProperty().getValue())
                onLostFocus();
            else
                gameView.updateStatus("");
        });

        stage.show();

//      sometimes the game gets stuck in paused mode, this ensures that when the game starts, it is unpaused
        gameModel.unPauseGame();
    }

    public static void main() {
        launch();
    }

    public void onLostFocus() {
        gameModel.stopGame();
        gameView.updateStatus("LOST FOCUS", Color.RED);
    }

    public void restart() {
        gameModel.restart();
        repaintView();
    }

    private void showPauseMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
            loader.setControllerFactory(c -> new PauseMenuController(this));

            scene.setRoot(loader.load());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDebugConsole() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DebugConsole.fxml"));
            loader.setControllerFactory(c -> new DebugConsoleController(gameModel));

            Scene debugScene = new Scene(loader.load());

            Stage debugStage = new Stage();
            debugStage.setScene(debugScene);
            debugStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void repaintView() {
        gameView.repaintView();
    }

    public Scene getScene() {
        return scene;
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> swingNode.setContent(gameView));
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                gameModel.moveLeft();
                break;
            case D:
                gameModel.moveRight();
                break;
            case ESCAPE:
                if (gameModel.isPaused()) {
                    gameModel.unPauseGame();
                } else {
                    gameModel.pauseGame();
                    showPauseMenu();
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
                    showDebugConsole();
            default:
                gameModel.getPlayer().stop();
        }
    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        gameModel.stopPlayer();
    }

    public void printMessage(String string, Color color) {
        gameView.updateStatus(string, color);
    }
}
