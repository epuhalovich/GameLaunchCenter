package fall2018.csc2017.slidingtiles.memory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MemoryBoard2 {

    private int NUM_ROWS;
    private int NUM_COLS;
    private Pairs[][] pairsSolution;
    private Pairs[][] pairsPuzzle;

    public MemoryBoard2(List<Pairs> tiles, int r, int c) {
        Iterator<Pairs> iter = tiles.iterator();
        this.NUM_ROWS = r;
        this.NUM_COLS = c;
        this.pairsSolution = new Pairs[NUM_ROWS][NUM_COLS];
        this.pairsPuzzle = new Pairs[NUM_ROWS][NUM_COLS];
        for (int row = 0; row != this.NUM_ROWS; row++) {
            for (int col = 0; col != this.NUM_COLS; col++) {
                this.pairsSolution[row][col] = iter.next();
                this.pairsPuzzle[row][col] = new Pairs(11);
            }
        }
    }

    public int getNUM_ROWS() {
        return NUM_ROWS;
    }

    public int getNUM_COLS() {
        return NUM_COLS;
    }

    public Pairs[][] getPairsPuzzle() {
        return pairsPuzzle;
    }
    public void flipCard(int position){
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        pairsPuzzle[row][col] = pairsSolution[row][col];
    }

    public void unFlipCard(int position){
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        pairsPuzzle[row][col] = new Pairs(11);
    }


    /**
     * Return the Pairs at (row, col)
     *
     * @param position
     * @return the tile at (row, col)
     */
    public Pairs getPairs(int position) {
        int row = position / this.getNUM_COLS();
        int col = position % this.getNUM_COLS();
        return pairsPuzzle[row][col];
    }

    public Pairs[][] getSolutionPairs() {
        return pairsSolution;
    }

    @Override
    public String toString() {
        return "SlidingTilesBoard{" +
                "tiles=" + Arrays.toString(pairsSolution) +
                '}';
    }

}
