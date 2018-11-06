package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private static int score;
    private Board board;
    private int numUndos;
    private Stack<Integer> undoDirectionStack;
    private Stack<Integer> undoPositionStack;
    private int maximumNumUndos = 3;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    public static BoardManager getLevel(String level){
        if(level.equals("Easy")){
            return new BoardManager(3, 3);
        }
        else if(level.equals("Medium")){
            return new BoardManager(4, 4);
        }
        else{
            return new BoardManager(5, 5);
        }
    }
    /**
     * Manage a new shuffled board.
     */
    BoardManager(int rows, int cols) {
        score = 0;
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = rows * cols;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(24));

        Collections.shuffle(tiles);
        this.board = new Board(tiles, rows, cols);

        this.numUndos = 3;
        this.undoDirectionStack = new Stack<>();
        this.undoPositionStack = new Stack<>();
    }

    /**
     * Return the score of a slidingtiles game.
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Return the numUndos left of a slidingtiles game.
     * @return numUndos
     */
    public int getNumUndos() {
        return numUndos;
    }

    /**
     * Set the numUndos of a slidingtiles game.
     */
    public void setNumUndos(int num) { this.numUndos = num; }

    /**
     * Set the maximumNumUndos of a slidingtiles game.
     */
    public void setMaximumNumUndos(int num) { this.maximumNumUndos = num; }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @SuppressLint("DefaultLocale")
    boolean puzzleSolved() {
        boolean solved = true;
        int i = 1;
        for (Tile t : board) {
            if (t.getId() != i) {
                solved = false;
            }
            i++;
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / board.NUM_COLS;
        int col = position % board.NUM_COLS;
        int blankId = 25;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);

    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     * Also adds an integer to the undo stack. 0,1,2,3 correspond to a tile swapped to
     * the above, left, below, and right respectively.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / board.NUM_ROWS;
        int col = position % board.NUM_COLS;
        int blankId = 25;

        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile below = row == board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);

        if (above != null && above.getId() == blankId) {
            board.swapTiles(row, col, row - 1, col);
            this.undoDirectionStack.push(0);
        } else if (left != null && left.getId() == blankId) {
            board.swapTiles(row, col, row, col - 1);
            this.undoDirectionStack.push(1);
        } else if (below != null && below.getId() == blankId) {
            board.swapTiles(row, col, row + 1, col);
            this.undoDirectionStack.push(2);
        }  else { // the tile to the right is the blank tile
            board.swapTiles(row, col, row, col + 1);
            this.undoDirectionStack.push(3);
        }
        this.undoPositionStack.push(position);
        if (this.numUndos < maximumNumUndos) {
            numUndos++;
        }
        score++;
    }

    /**
     * Checks if there are undo moves left, and if so, swaps tiles in the reverse order of
     * the last move.
     */
    void tryUndo() {
        if (this.numUndos > 0 && !(this.undoDirectionStack.empty() && this.undoPositionStack.empty())) {
            numUndos--;
            int position = undoPositionStack.pop();
            int direction = undoDirectionStack.pop();
            int row = position / board.NUM_ROWS;
            int col = position % board.NUM_COLS;

            switch (direction) {
                case 0: // Swap blank tile with ABOVE.
                    board.swapTiles(row, col, row - 1, col);
                    score ++;
                    break;
                case 1: // Swap blank tile with LEFT.
                    board.swapTiles(row, col, row, col - 1);
                    score ++;
                    break;
                case 2: // Swap blank tile with BELOW.
                    board.swapTiles(row, col, row + 1, col);
                    score ++;
                    break;
                default: // Swap blank tile with RIGHT.
                    board.swapTiles(row, col, row, col + 1);
                    score ++;
                    break;
            }
        }
    }

}
