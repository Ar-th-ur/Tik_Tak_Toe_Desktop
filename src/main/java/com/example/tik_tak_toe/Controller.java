package com.example.tik_tak_toe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Controller {
    public VBox      grid;
    public ImageView topLeftCell, topCenterCell, topRightCell, centerLeftCell, centerCenterCell, centerRightCell, bottomLeftCell, bottomCenterCell, bottomRightCell;

    private final Game                    game;
    private final Map<Integer, ImageView> pairs;

    public Controller() {
        this.game = new Game();
        this.pairs = new HashMap<>();
    }

    /**
     * Processes the click and sets image
     *
     * @param coordinates coordinates of the cell what was clicked
     * @param imageView   imageView which is located at these coordinates
     */
    private void pressTo(int coordinates, ImageView imageView) {
        game.move(coordinates);
        if (game.getCurrentMove() == 1) {
            imageView.setImage(GameResources.CROSS);
        } else {
            imageView.setImage(GameResources.TOE);
        }

        if (game.isFinished()) {
            if (!game.isDraw()) {
                colorWinningCells();
            }
            restartGame();
        }
    }

    /**
     * Clears the grid after two second
     */
    private void restartGame() {
        Platform.runLater(() -> {
            ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(10);
            executor.schedule(() -> {
                for (ImageView imageView : pairs.values()) {
                    imageView.setImage(null);
                }
                pairs.clear();
                game.restartGame();
            }, 2, TimeUnit.SECONDS);
        });
    }

    /**
     * Colors winning cells
     */
    private void colorWinningCells() {
        int[] winnerPositions = game.getWinningPositions();
        Image image = game.getWinner() == 1 ? GameResources.CROSS_WIN : GameResources.TOE_WIN;
        for (int winnerPosition : winnerPositions) {
            pairs.get(winnerPosition).setImage(image);
        }
    }

    /**
     * Processes the first click
     *
     * @param coordinates coordinates of the cell what was clicked
     * @param field       field which is located at these coordinates
     */
    private void firstPress(int coordinates, ImageView field) {
        if (!pairs.containsValue(field)) {
            pairs.put(coordinates, field);
            pressTo(coordinates, field);
        }
    }

    @FXML
    protected void onTopLeftPressed() {
        firstPress(0, topLeftCell);
    }

    @FXML
    protected void onTopCenterPressed() {
        firstPress(1, topCenterCell);
    }

    @FXML
    protected void onTopRightPressed() {
        firstPress(2, topRightCell);
    }

    @FXML
    protected void onCenterLeftPressed() {
        firstPress(3, centerLeftCell);
    }

    @FXML
    protected void onCenterCenterPressed() {
        firstPress(4, centerCenterCell);
    }

    @FXML
    protected void onCenterRightPressed() {
        firstPress(5, centerRightCell);
    }

    @FXML
    protected void onBottomLeftPressed() {
        firstPress(6, bottomLeftCell);
    }

    @FXML
    protected void onBottomCenterPressed() {
        firstPress(7, bottomCenterCell);
    }

    @FXML
    protected void onBottomRightPressed() {
        firstPress(8, bottomRightCell);
    }
}