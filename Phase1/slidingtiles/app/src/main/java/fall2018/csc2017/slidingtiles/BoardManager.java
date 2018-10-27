package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

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
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = rows * cols;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(24));

        Collections.shuffle(tiles);
        this.board = new Board(tiles, rows, cols);
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

        if (above != null && above.getId() == blankId) {
            board.swapTiles(row, col, row - 1, col);
        } else if (below != null && below.getId() == blankId) {
            board.swapTiles(row, col, row + 1, col);
        } else if (left != null && left.getId() == blankId) {
            board.swapTiles(row, col, row, col - 1);
        } else { // the tile to the right is the blank tile
            board.swapTiles(row, col, row, col + 1);
        }
    }

}
