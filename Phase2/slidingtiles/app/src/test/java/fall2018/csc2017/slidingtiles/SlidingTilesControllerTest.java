package fall2018.csc2017.slidingtiles;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import junit.framework.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesController;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.Tile;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the sliding tiles scoreboard.
 *
 * Following methods at:
 * - http://d.android.com/tools/testing
 * - https://developer.android.com/training/testing/unit-testing/local-unit-tests
 */
public class SlidingTilesControllerTest {

    /**
     * The SlidingTilesController being tested
     */
    SlidingTilesController controller = new SlidingTilesController();

    /**
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles(4,4);
        SlidingTilesBoard slidingTilesBoard = new SlidingTilesBoard(tiles, 4, 4);
        SlidingTilesManager slidingTilesManager = new SlidingTilesManager(slidingTilesBoard);
        controller.setGameManager(slidingTilesManager);
    }

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


    @Test
    public void testSetUpSlidingTiles(){
        controller.setUpBoard("Easy");
        int boardDimension = controller.getGameManager().getSlidingTilesBoard().NUM_ROWS * controller.getGameManager().getSlidingTilesBoard().NUM_COLS;
        Assert.assertEquals(9, boardDimension);
        Assert.assertEquals(3, controller.getGameManager().getNumUndos());
    }

    @Test
    public void testUndoCount(){
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        Random random = new Random();
        int numUndos = random.nextInt(6);
        slidingTilesManager.setNumUndos(numUndos);

        for (int i = 0; i < numUndos; i++) {
            slidingTilesManager.touchMove(random.nextInt(3));
        }
        for (int i = 0; i < numUndos; i++) {
            slidingTilesManager.tryUndo();
        }

        Assert.assertEquals(0, slidingTilesManager.getNumUndos());
    }

    @Test
    public void testCheckToAddScore(){
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        when(slidingTilesManager.isGameOver()).thenReturn(true);
        when(slidingTilesManager.getScore()).thenReturn(25);
        controller.setGameManager(slidingTilesManager);
        controller.checkToAddScore(scoreboard, user);
        ArrayList<Score> actual = scoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCreateTileButtons(){
//        setUpCorrect();
//        View view = mock(View.class);
//        when(view.getContext()).thenReturn(mock(Context.class));
//        doCallRealMethod().when(view).setBackgroundResource(any(Integer.class));
//        controller.createTileButtons(view.getContext());
//        ArrayList<Button> buttons = controller.getTileButtons();
//        for(int i = 0; i<buttons.size(); i++){
//            Assert.assertEquals(buttons.get(0).getBackground(),view.getContext().getResources().getDrawable(R.drawable.tile_1));
//        }

    }

    @Test
    public void testRegister(){
        controller = new SlidingTilesController();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try{
            controller.register(observer);
            controller.register(null);
        }catch(NullPointerException e){
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }

}
