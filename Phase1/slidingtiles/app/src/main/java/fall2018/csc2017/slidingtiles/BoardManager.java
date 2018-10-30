package fall2018.csc2017.slidingtiles;

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
    private int score;
    private Board board;
    private Stack<Integer> undoStack;

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

    /**
     * Manage a new shuffled board.
     */
    BoardManager(int rows, int cols) {
        this.score = 0;
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = rows * cols;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(24));

        Collections.shuffle(tiles);
        this.board = new Board(tiles, rows, cols);
        this.undoStack = new Stack<Integer>();
    }

    /**
     * Return the score of a slidingtiles game
     * @return score
     */

    public int getScore() {
        return score;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
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
     * the right, above, left, and below respectively.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / board.NUM_ROWS;
        int col = position % board.NUM_COLS;
        int blankId = 25;

        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);

        score++;

        if (above != null && above.getId() == blankId) {
            board.swapTiles(row, col, row - 1, col);
            this.undoStack.push(1);
        } else if (below != null && below.getId() == blankId) {
            board.swapTiles(row, col, row + 1, col);
            this.undoStack.push(3);
        } else if (left != null && left.getId() == blankId) {
            board.swapTiles(row, col, row, col - 1);
            this.undoStack.push(2);
        } else { // the tile to the right is the blank tile
            board.swapTiles(row, col, row, col + 1);
            this.undoStack.push(0);
        }
    }

    void tryUndo() {
        if (!this.undoStack.empty()) {
            switch (undoStack.pop()) {
                case 0:
                    System.out.println(0);
                    break;
                case 1:
                    System.out.println(1);
                    break;
                case 2:
                    System.out.println(2);
                    break;
                default: // case 3
                    System.out.println(3);
                    break;
            }
        }
        else {
            System.out.println("No undos left.");
        }
    }

}
