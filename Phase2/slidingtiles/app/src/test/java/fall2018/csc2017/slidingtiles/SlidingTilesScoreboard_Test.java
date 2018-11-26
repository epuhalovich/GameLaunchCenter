package fall2018.csc2017.slidingtiles;

import junit.framework.Assert;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesScoreboard;

/**
 * Unit tests for the sliding tiles scoreboard.
 *
 * Following methods at:
 * - http://d.android.com/tools/testing
 * - https://developer.android.com/training/testing/unit-testing/local-unit-tests
 */
public class SlidingTilesScoreboard_Test {

    /** the scoreboard for testing. */
    SlidingTilesScoreboard slidingTilesScoreboard;

    private void makePopulatedScoreboard(){
        ArrayList<Score> testScores = new ArrayList<>();
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player2", 75));
        testScores.add(new Score("player1",100));
        slidingTilesScoreboard = new SlidingTilesScoreboard(testScores);
    }

    @Test
    public void testAddScore(){
        makePopulatedScoreboard();
        slidingTilesScoreboard.addScore("player2", 25);
        Score expectedFirst = new Score("player2", 25);
        Assert.assertEquals(expectedFirst, slidingTilesScoreboard.getGlobalScores().get(0));
        Score expectedLast = new Score("player1", 100);
        Assert.assertEquals(expectedLast, slidingTilesScoreboard.getGlobalScores().get(5));
    }

    @Test
    public void testGetScoreValues(){
        makePopulatedScoreboard();
        User userMock = mock(User.class);
        when(userMock.getAccount()).thenReturn("player1");
        String expectedGlobal = "player1: 50\nplayer1: 50\nplayer1: 50\nplayer2: 75\nplayer1: 100\n";
        Assert.assertEquals(expectedGlobal, slidingTilesScoreboard.getScoreValues(false, userMock));
        String expectedUser = "player1: 50\nplayer1: 50\nplayer1: 50\nplayer1: 100\n";
        Assert.assertEquals(expectedUser, slidingTilesScoreboard.getScoreValues(true, userMock));
    }




    }



