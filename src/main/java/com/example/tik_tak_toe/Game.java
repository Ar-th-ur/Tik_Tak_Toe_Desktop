package com.example.tik_tak_toe;

import java.util.Arrays;

public class Game {
    private final int   X = 1;
    private final int   O = -1;
    private final int[] grid;
    private       byte  moveCount;
    private       int   currentMove;
    private final int[] winningPositions;

    public Game() {
        this.grid = new int[9];
        this.moveCount = 0;
        this.currentMove = O;
        this.winningPositions = new int[3];
    }

    /**
     * Processes move
     *
     * @param coordinates coordinates of move
     */
    public void move(int coordinates) {
        currentMove = currentMove == O ? X : O;
        grid[coordinates] = currentMove;
        moveCount++;
    }

    /**
     * Returns true if there are no empty cells
     */
    public boolean isDraw() {
        return moveCount == 9;
    }

    /**
     * Clears grid
     */
    public void restartGame() {
        Arrays.fill(grid, 0);
        Arrays.fill(winningPositions, 0);
        moveCount = 0;
        currentMove = O;
    }

    /**
     * Checks if game has finished
     *
     * @return true if game is finished
     */
    public boolean isFinished() {
        return isWon(X) || isWon(O) || isDraw();
    }

    /**
     * Determines whether the given sign has won
     *
     * @param sign the checking sign
     * @return true if the given sign has won
     */
    private boolean isWon(int sign) {
        return isDiagonal(sign) || isColumn(sign) || isLine(sign);
    }

    /**
     * Checks if it won by diagonal
     *
     * @param sign the checking sign
     */
    private boolean isDiagonal(int sign) {
        if (grid[4] == sign && grid[0] == sign && grid[8] == sign) {
            winningPositions[0] = 0;
            winningPositions[1] = 4;
            winningPositions[2] = 8;
            return true;
        } else if (grid[4] == sign && grid[2] == sign && grid[6] == sign) {
            winningPositions[0] = 4;
            winningPositions[1] = 2;
            winningPositions[2] = 6;
            return true;
        }
        return false;
    }

    /**
     * Checks if it won by line
     *
     * @param sign the checking sign
     */
    private boolean isLine(int sign) {
        for (int i = 0; i < 9; i += 3) {
            if (grid[i] == sign && grid[i + 1] == sign && grid[i + 2] == sign) {
                winningPositions[0] = i;
                winningPositions[1] = i + 1;
                winningPositions[2] = i + 2;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if it won column
     *
     * @param sign the checking sign
     */
    private boolean isColumn(int sign) {
        for (int i = 0; i < 3; i++) {
            if (grid[i] == sign && grid[i + 3] == sign && grid[i + 6] == sign) {
                winningPositions[0] = i;
                winningPositions[1] = i + 3;
                winningPositions[2] = i + 6;
                return true;
            }
        }
        return false;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public int[] getWinningPositions() {
        return winningPositions;
    }

    public int getWinner() {
        return grid[winningPositions[0]];
    }
}
