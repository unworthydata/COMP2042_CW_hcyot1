package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Controller;
import com.game.comp2042_cw_hcyot1.DebugConsoleController;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameController extends Application {
    private Scene scene;
    private Stage stage;
    StackPane root;

    private GameModel gameModel = new GameModel(new Rectangle(0, 0, 600, 450),
            new Point(300, 430), this);
    private GameView gameView;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        gameView = new GameView(gameModel);
        StackPane root = gameView.getRoot();

        scene = new Scene(root, Controller.DEF_WIDTH, Controller.DEF_HEIGHT);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);

        stage.focusedProperty().addListener(event -> {
            if (!stage.focusedProperty().getValue())
                onLostFocus();
            else
                gameView.updateStatus("FOCUS REGAINED", Color.HOTPINK);
        });
        stage.onCloseRequestProperty().set(event -> gameModel.saveScores());

        stage.setScene(scene);
        stage.show();

//      sometimes the game gets stuck in paused mode, this ensures that when the game starts, it is unpaused
        gameModel.unPauseGame();
        gameView.displayPaused();
    }

    public void onLostFocus() {
        gameModel.stopGame();
        gameView.updateStatus("LOST FOCUS", Color.RED);
    }

    public void restart() {
        gameModel.restart();
        repaintView();
    }

    public void showDebugConsole() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DebugConsole.fxml"));
            loader.setControllerFactory(c -> new DebugConsoleController(gameModel));

            Scene debugScene = new Scene(loader.load());

            Stage debugStage = new Stage();
            debugStage.setResizable(false);
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

    public void updateStatus(String string, Color color) {
        gameView.updateStatus(string, color);
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
                    gameView.displayPaused();
                    showPauseMenu();
                }
                break;
            case SPACE:
                if (!gameModel.isPaused())
                    if (gameModel.isRunning()) {
                        gameModel.stopGame();
                        gameView.displayPaused();
                    }
                    else {
                        gameModel.startGame();
                        gameView.displayUnpaused();
                    }
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

    public void saveScores() {
        gameModel.saveScores();
    }

    public void displayNewHighScore(int newHighScore) {
        gameView.displayNewHighScore(newHighScore);
    }
}
