package fall2018.csc2017.slidingtiles;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesController;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;

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

}
