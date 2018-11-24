package fall2018.csc2017.slidingtiles;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesController;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesScoreboard;
import fall2018.csc2017.slidingtiles.slidingtiles.Tile;

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


    @Test
    public void testSetUpSlidingTiles(){
        controller.setUpSlidingTiles("Easy");
        int boardDimension = controller.getSlidingTilesManager().getSlidingTilesBoard().NUM_ROWS * controller.getSlidingTilesManager().getSlidingTilesBoard().NUM_COLS;
        Assert.assertEquals(9, boardDimension);
        Assert.assertEquals(3, controller.getSlidingTilesManager().getNumUndos());
    }

    @Test
    public void testCheckToAddScore(){
        SlidingTilesScoreboard slidingTilesScoreboard = new SlidingTilesScoreboard();
        String user = "player1";
        SlidingTilesManager slidingTilesManager = mock(SlidingTilesManager.class);
        when(slidingTilesManager.isGameOver()).thenReturn(true);
        when(slidingTilesManager.getScore()).thenReturn(25);
        controller.setSlidingTilesManager(slidingTilesManager);
        controller.checkToAddScore(slidingTilesScoreboard, user);
        ArrayList<Score> actual = slidingTilesScoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }

}
