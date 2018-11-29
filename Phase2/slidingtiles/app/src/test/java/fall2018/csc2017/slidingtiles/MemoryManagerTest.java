package fall2018.csc2017.slidingtiles;



import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.memory.MemoryBoard;
import fall2018.csc2017.slidingtiles.memory.MemoryManager;
import fall2018.csc2017.slidingtiles.memory.Pairs;

public class MemoryManagerTest {

    /** The memory manager for testing **/
    MemoryManager manager;

    private void setUp(int rows, int cols){
        List<Pairs> pairs = new ArrayList<>();
        int numTiles = rows * cols;
        int numPairs = numTiles / 2;
        for (int PairsId = 0; PairsId != numPairs; PairsId++) {
            pairs.add(new Pairs(PairsId));
            pairs.add(new Pairs(PairsId));
        }
        manager = new MemoryManager(new MemoryBoard(pairs, rows, cols));
    }

    @Test
    public void testGetLevel(){
        MemoryManager easy = MemoryManager.getLevel("Easy");
        Assert.assertEquals(2, easy.getMemoryBoard().getNUM_COLS());
        MemoryManager meduim = MemoryManager.getLevel("Medium");
        Assert.assertEquals(4, meduim.getMemoryBoard().getNUM_COLS());
        MemoryManager hard = MemoryManager.getLevel("Hard");
        Assert.assertEquals(6, hard.getMemoryBoard().getNUM_COLS());
    }

    @Test
    public void testIsValidTap(){
        setUp(4,4);
        manager.getMemoryBoard().flipCard(1);
        Assert.assertFalse(manager.isValidTap(1));
        Assert.assertTrue(manager.isValidTap(2));
    }

    @Test
    public void testTouchMoveNoMatch(){
        setUp(2,2);
        manager.touchMove(0);
        Assert.assertEquals(1, manager.getFlipCount());
        Assert.assertEquals(0, manager.getFirstFlippedPosition());
        manager.touchMove(2);
        Assert.assertEquals(2,manager.getFlipCount());
        Assert.assertEquals(2, manager.getSecondFlippedPosition());
        manager.touchMove(1);
        Pairs blank = new Pairs(18);
        Assert.assertEquals(blank.getId(), manager.getMemoryBoard().getPairs(0).getId());
        Assert.assertEquals(blank.getId(), manager.getMemoryBoard().getPairs(2).getId());
        Assert.assertEquals(1, manager.getScore());
    }

    @Test
    public void testTouchMoveMatch(){
        setUp(2,2);
        manager.touchMove(0);
        manager.touchMove(1);
        manager.touchMove(2);
        Assert.assertEquals(1, manager.getNumMatches());
    }

    @Test
    public void testGameNotOver(){
        setUp(2,2);
        Assert.assertFalse(manager.isGameOver());
    }

    public void testGameOver(){
        setUp(2,2);
        manager.touchMove(0);
        manager.touchMove(1);
        manager.touchMove(2);
        manager.touchMove(3);
        Assert.assertTrue(manager.isGameOver());
    }
}
