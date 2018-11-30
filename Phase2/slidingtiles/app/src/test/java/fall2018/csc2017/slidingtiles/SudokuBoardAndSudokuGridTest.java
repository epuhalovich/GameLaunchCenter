package fall2018.csc2017.slidingtiles;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesBoard;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.Tile;
import fall2018.csc2017.slidingtiles.sudoku.SudokuBoard;
import fall2018.csc2017.slidingtiles.sudoku.SudokuGrid;
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

    private void setUpSudokuManager(){
            sudokuManager = new SudokuManager(3);
    }

    @Test
    public void testGetEmptySpot(){
        setUpSudokuManager();
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

    @Test
    public void testTouchMove() {
        setUpSudokuManager();
        sudokuManager.setNumberToFill("3");
        Assert.assertEquals(0, sudokuManager.getUndoPositionStack().size());
        Assert.assertEquals(0, sudokuManager.getScore());
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                int position = row * 9 + col;
                if (sudokuManager.getEmptySpot(row, col) && !(sudokuManager.checkRepeated(position))) {
                    sudokuManager.touchMove(position);
                    Assert.assertEquals("3", sudokuManager.
                            getSudokuBoard().getPuzzleSudoku()[row][col].getNumber());
                    Assert.assertEquals(2, sudokuManager.getUndoPositionStack().size());
                    Assert.assertEquals(col, (int) sudokuManager.getUndoPositionStack().pop());
                    Assert.assertEquals(row, (int) sudokuManager.getUndoPositionStack().pop());
                    Assert.assertEquals(1, sudokuManager.getScore());
                    Assert.assertEquals("", sudokuManager.getNumberToFill());
                    break;
                }
            }
        }

    }

//    @Test
//    public void testCheckRepeated(){
//        setUpSudokuManager();
//        Assert.assertTrue(sudokuManager.checkRepeated(2 * 9 + 3));
//        sudokuManager.setNumberToFill("4");
//        for (int col = 0; col != 9; col++) {
//            sudokuManager.getSudokuBoard().getPuzzleSudoku()[0][col].setNumber("4");
//        }
//        Assert.assertFalse(sudokuManager.checkRepeated(0));
//
//    }

    @Test
    public void testSetUpBackgrounds(){
        setUpSudokuManager();
        sudokuManager.setUpBackgrounds();
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                Assert.assertEquals(sudokuManager.getSudokuBoard().
                        getPuzzleSudoku()[row][col].getBackground(), (int) sudokuManager.
                        getBackgrounds().get(row * 9 + col));
            }
        }
    }


    @Test
    public void testSetOriginal(){
        setUpSudokuManager();
        sudokuManager.setUpBackgrounds();
        sudokuManager.setUpBackground(3 * 9 + 3);
        sudokuManager.setOriginal(sudokuManager.getSudokuBoard().getPuzzleSudoku());
        for(int row = 0 ; row != 9; row++){
            for(int col = 0; col != 9; col++){
                Assert.assertEquals( sudokuManager.getSudokuBoard().
                        getClonePuzzle()[row][col].getBackground(),
                        sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].getBackground());
            }
        }

    }


    @Test
    public void testSetUpBackground(){
        setUpSudokuManager();
        sudokuManager.setUpBackground(3 * 9 + 3);
        Assert.assertEquals( R.drawable.button_selected
                ,sudokuManager.getSudokuBoard().
                getPuzzleSudoku()[3][3].getBackground());
        for(int line = 0; line != 9; line ++){
            if(line != 3){
            Assert.assertEquals( R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                    getPuzzleSudoku()[3][line].getBackground());
            Assert.assertEquals( R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                    getPuzzleSudoku()[line][3].getBackground());}
        }
        for(int row = 3; row != 6; row ++){
            for(int col = 3; col != 6; col++){
                if(!(row == 3 && col == 3)){
                Assert.assertEquals(R.drawable.button_pressed, sudokuManager.getSudokuBoard().
                        getPuzzleSudoku()[row][col].getBackground());
                }
            }
        }
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpSudokuManager();
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

    @Test
    public void testIsGameOver(){
        setUpSudokuManager();
        Assert.assertFalse(sudokuManager.isGameOver());
        for(int row = 0; row != 9; row++){
            for(int col = 0; col != 9; col++){
                if(sudokuManager.getEmptySpot(row,col)){
                    String number = sudokuManager.getSudokuBoard().getSolutionSudoku()[row][col].getNumber();
                    sudokuManager.getSudokuBoard().getPuzzleSudoku()[row][col].setNumber(number);
                }
            }
        }
        Assert.assertTrue(sudokuManager.isGameOver());

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
