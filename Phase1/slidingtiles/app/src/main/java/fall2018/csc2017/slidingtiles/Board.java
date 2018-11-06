package fall2018.csc2017.slidingtiles;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    int NUM_ROWS;

    /**
     * The number of rows.
     */
    int NUM_COLS;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles, int r, int c) {
        Iterator<Tile> iter = tiles.iterator();
        this.NUM_ROWS = r;
        this.NUM_COLS = c;
        this.tiles = new Tile[NUM_ROWS][NUM_COLS];

        for (int row = 0; row != this.NUM_ROWS; row++) {
            for (int col = 0; col != this.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tile1 = tiles[row1][col1];
        Tile tile2 = tiles[row2][col2];

        tiles[row1][col1] = tile2;
        tiles[row2][col2] = tile1;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    private class TileIterator implements Iterator<Tile> {
        /**
         * The current row index.
         */
        private int currentRow;
        /**
         * The current column index.
         */
        private int currentColumn;

        /**
         * An iterator over the tiles in the board.
         * Starts the indices pointing to the top left tile in the 2D tiles array.
         */
        public TileIterator() {
            this.currentRow = 0;
            this.currentColumn = 0;
        }

        @Override
        public boolean hasNext() {
            return (currentRow < (NUM_ROWS - 1) || currentColumn < (NUM_COLS - 1));
        } // Using constants minus 1 to match indices starting from 0.

        @Override
        public Tile next() {
            Tile thisTile = tiles[currentRow][currentColumn];
            if (hasNext()) {
                if (currentColumn == (NUM_COLS - 1)) {
                    currentRow++;
                    currentColumn = 0;
                } else {
                    currentColumn++;
                }
                return thisTile;
            }
            throw new NoSuchElementException();
        }
    }
}

