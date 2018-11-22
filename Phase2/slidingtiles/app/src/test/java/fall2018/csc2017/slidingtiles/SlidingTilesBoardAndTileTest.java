package fall2018.csc2017.slidingtiles;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.Tile;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SlidingTilesBoardAndTileTest {

    /** The board manager for testing. */
    SlidingTilesManager slidingTilesManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles(int row, int col) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = row * col;
        for (int tileNum = 0; tileNum != numTiles -1; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(24));

        return tiles;
    }

    /**
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles(4,4);
        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(tiles, 4, 4);
        slidingTilesManager = new SlidingTilesManager(slidingTilesBoard);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        slidingTilesManager.getSlidingTilesBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        Assert.assertEquals(true, slidingTilesManager.isGameOver());
        swapFirstTwoTiles();
        Assert.assertEquals(false, slidingTilesManager.isGameOver());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        Assert.assertEquals(1, slidingTilesManager.getSlidingTilesBoard().getTile(0, 0).getId());
        Assert.assertEquals(2, slidingTilesManager.getSlidingTilesBoard().getTile(0, 1).getId());
        slidingTilesManager.getSlidingTilesBoard().swapTiles(0, 0, 0, 1);
        Assert.assertEquals(2, slidingTilesManager.getSlidingTilesBoard().getTile(0, 0).getId());
        Assert.assertEquals(1, slidingTilesManager.getSlidingTilesBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        Assert.assertEquals(15, slidingTilesManager.getSlidingTilesBoard().getTile(3, 2).getId());
        Assert.assertEquals(25, slidingTilesManager.getSlidingTilesBoard().getTile(3, 3).getId());
        slidingTilesManager.getSlidingTilesBoard().swapTiles(3, 3, 3, 2);
        Assert.assertEquals(25, slidingTilesManager.getSlidingTilesBoard().getTile(3, 2).getId());
        Assert.assertEquals(15, slidingTilesManager.getSlidingTilesBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();
        Assert.assertEquals(true, slidingTilesManager.isValidTap(11));
        Assert.assertEquals(true, slidingTilesManager.isValidTap(14));
        Assert.assertEquals(false, slidingTilesManager.isValidTap(10));
    }
}

