package com.game.comp2042_cw_hcyot1.fxMenus;

import com.game.comp2042_cw_hcyot1.brick.Brick;
import com.game.comp2042_cw_hcyot1.painter.BasicPainter;
import com.game.comp2042_cw_hcyot1.painter.Painter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.io.IOException;

public class GameController extends Application {
    private Scene scene;
    private Stage stage;
    private GameModel gameModel = new GameModel(new Rectangle(0, 0, 600, 450), new Point(300, 430));

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        Group root = new Group();
        Canvas canvas = new Canvas(600, 450);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        root.getChildren().add(canvas);

        scene = new Scene(root);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);

        stage.setScene(scene);
        stage.focusedProperty().addListener(event -> onLostFocus());

        if (gameModel.isPaused())
            pause();

        GameView view = new GameView();
        view.draw(g2d, gameModel);
    }

    public static void main() {
        launch();
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
}
