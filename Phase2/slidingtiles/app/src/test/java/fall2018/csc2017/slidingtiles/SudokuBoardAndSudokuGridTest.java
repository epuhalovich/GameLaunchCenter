package fall2018.csc2017.slidingtiles;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.Tile;
import fall2018.csc2017.slidingtiles.sudoku.SudokuBoard;
import fall2018.csc2017.slidingtiles.sudoku.SudokuManager;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SudokuBoardAndSudokuGridTest {

    /** The sudokuManager for testing. */
    private SudokuManager sudokuManager;



    private SudokuManager setupSudokuManager(){
        if(sudokuManager == null){
         sudokuManager = new SudokuManager(3);
        }
        return sudokuManager;
    }

    @Test
    public void testGetEmptySpot(){
        setupSudokuManager();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getNumber().equals(""))
                {
                    Assert.assertTrue(sudokuManager.getEmptySpot(row,col));
                }
                else{
                    Assert.assertFalse(sudokuManager.getEmptySpot(row,col));
                }
            }
        }
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setupSudokuManager();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getEmptySpot(row,col)){
                    Assert.assertTrue(sudokuManager.isValidTap(row * 9 + col));
                }
                else{
                Assert.assertFalse(sudokuManager.isValidTap(row * 9 + col));}
            }
        }
    }

//    @Test
//    public void testGetLevel(){
//        SudokuManager easy = SudokuManager.getLevel("Easy");
//        Assert.assertEquals(3, easy.SudokuManager().getNUM_COLS());
//        SlidingTilesManager meduim = SlidingTilesManager.getLevel("Medium");
//        Assert.assertEquals(4, meduim.getSlidingTilesBoard().getNUM_COLS());
//        SlidingTilesManager hard = SlidingTilesManager.getLevel("Hard");
//        Assert.assertEquals(5, hard.getSlidingTilesBoard().getNUM_COLS());
//    }


}
