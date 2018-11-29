package fall2018.csc2017.slidingtiles;

import junit.framework.Assert;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

/**
 * Unit tests for the sliding tiles scoreboard.
 *
 * Following methods at:
 * - http://d.android.com/tools/testing
 * - https://developer.android.com/training/testing/unit-testing/local-unit-tests
 */
public class SlidingTilesScoreboard_Test {

    /** the scoreboard for testing. */
    Scoreboard scoreboard;

    private void makePopulatedScoreboard(){
        ArrayList<Score> testScores = new ArrayList<>();
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player1",50));
        testScores.add(new Score("player2", 75));
        testScores.add(new Score("player1",100));
        scoreboard = new Scoreboard(testScores);
    }

    @Test
    public void testAddScore(){
        makePopulatedScoreboard();
        scoreboard.addScore("player2", 25);
        Score expectedFirst = new Score("player2", 25);
        Assert.assertEquals(expectedFirst, scoreboard.getGlobalScores().get(0));
        Score expectedLast = new Score("player1", 100);
        Assert.assertEquals(expectedLast, scoreboard.getGlobalScores().get(5));
    }

    @Test
    public void testGetScoreValues(){
        makePopulatedScoreboard();
        User userMock = mock(User.class);
        when(userMock.getAccount()).thenReturn("player1");
        String expectedGlobal = "player1: 50\nplayer1: 50\nplayer1: 50\nplayer2: 75\nplayer1: 100\n";
        Assert.assertEquals(expectedGlobal, scoreboard.getScoreValues(false, userMock));
        String expectedUser = "player1: 50\nplayer1: 50\nplayer1: 50\nplayer1: 100\n";
        Assert.assertEquals(expectedUser, scoreboard.getScoreValues(true, userMock));
    }

    @Test
    public void testRegister(){
        scoreboard = new Scoreboard();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try{
            scoreboard.register(observer);
            scoreboard.register(null);
        }catch(NullPointerException e){
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }




    }



