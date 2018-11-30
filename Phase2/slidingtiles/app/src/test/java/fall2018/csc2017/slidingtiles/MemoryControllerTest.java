package fall2018.csc2017.slidingtiles;

import junit.framework.Assert;
import fall2018.csc2017.slidingtiles.memory.MemoryController;
import fall2018.csc2017.slidingtiles.memory.MemoryManager;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemoryControllerTest {
    MemoryController controller;

    @Test
    public void testRegister() {
        controller = new MemoryController();
        boolean thrownNull = false;
        PhaseTwoObserver observer = mock(PhaseTwoObserver.class);
        try {
            controller.register(observer);
            controller.register(null);
        } catch (NullPointerException e) {
            thrownNull = true;
        }
        Assert.assertTrue(thrownNull);
    }

    @Test
    public void testSeutUpBoard() {
        controller = new MemoryController();
        controller.setUpBoard("Easy");
        int boardDimensionEasy = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(4, boardDimensionEasy);
        controller.setUpBoard("Medium");
        int boardDimensionMedium = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(16, boardDimensionMedium);
        controller.setUpBoard("Hard");
        int boardDimensionHard = controller.getGameManager().getMemoryBoard().getNUM_ROWS() * controller.getGameManager().getMemoryBoard().getNUM_COLS();
        Assert.assertEquals(36, boardDimensionHard);
    }

    @Test
    public void testCheckToAddScoreNotFinished(){
        controller = new MemoryController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        MemoryManager memoryManager = mock(MemoryManager.class);
        when(memoryManager.isGameOver()).thenReturn(false);
        controller.setGameManager(memoryManager);
        controller.checkToAddScore(scoreboard, user);
        Assert.assertEquals(scoreboard.getGlobalScoreboard().size(), 0);
    }

    @Test
    public void testCheckToAddScore(){
        controller = new MemoryController();
        Scoreboard scoreboard = new Scoreboard();
        String user = "player1";
        MemoryManager memoryManager = mock(MemoryManager.class);
        when(memoryManager.isGameOver()).thenReturn(true);
        when(memoryManager.getScore()).thenReturn(25);
        controller.setGameManager(memoryManager);
        controller.checkToAddScore(scoreboard, user);
        ArrayList<Score> actual = scoreboard.getGlobalScores();
        ArrayList<Score> expected = new ArrayList<>();
        expected.add((new Score("player1",25)));
        Assert.assertEquals(actual, expected);
    }
}
