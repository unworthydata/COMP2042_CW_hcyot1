package com.game.comp2042_cw_hcyot1.game;

import com.game.comp2042_cw_hcyot1.Controller;
import com.game.comp2042_cw_hcyot1.DebugConsoleController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**
 * @see GameView
 * @see GameModel
 */
public class GameController extends Application {
    private Scene scene;
    private Stage stage;

    private GameView gameView;
    private GameModel gameModel = new GameModel(
            new Rectangle(0, 0, Controller.DEF_WIDTH, Controller.DEF_HEIGHT),
            new Point(300, 430),
            this
    );

    /**
     * Instantiates the GameView, adds keystroke and focus listeners, and starts the game.
     * Also makes sure the high scores are saved in case the window closes mid-game.
     *
     * @see GameView
     */
    @Override
    public void start(Stage stage) {
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

    /**
     * Called when the window loses focus.
     * Stops the game and tells the {@link GameView} ({@link #gameView})
     * to handle losing focus (by updating the status message).
     *
     * @see GameView#onLostFocus()
     */
    public void onLostFocus() {
        gameModel.stopGame();
        gameView.onLostFocus();
    }

    /**
     * Tells the {@link GameModel} ({@link #gameModel}) to restart the game, then tells the {@link GameView} ({@link #gameView})  to update itself.
     */
    public void restart() {
        gameModel.restart();
        gameView.repaintView();
    }

    /**
     * Shows the debug console in a new window. In order for the debug console to be
     * linked to the current game, the {@link GameModel} ({@link #gameModel})
     * is passed as an argument to the {@link DebugConsoleController}
     */
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

    /**
     * Tells the {@link GameView} ({@link #gameView}) to update itself
     */
    public void repaintView() {
        gameView.repaintView();
    }

    /**
     * Tells the {@link GameView} ({@link #gameView}) to update the status message
     * @param string The message to be displayed
     * @param color The color of the string to be displayed
     */
    public void updateStatus(String string, Color color) {
        gameView.updateStatus(string, color);
    }

    /**
     * Tells the {@link GameModel} ({@link #gameModel}) to save scores. Used in the pause menu when exiting.
     * @see PauseMenuController#exitToMainMenu(ActionEvent)
     */
    public void saveScores() {
        gameModel.saveScores();
    }

    /**
     * Tells the Tells the {@link GameView} ({@link #gameView})
     * to update because there is a new high score.
     *
     * @param newHighScore The new high score
     */
    public void displayNewHighScore(int newHighScore) {
        gameView.displayNewHighScore(newHighScore);
    }

    public Scene getScene() {
        return scene;
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
}
