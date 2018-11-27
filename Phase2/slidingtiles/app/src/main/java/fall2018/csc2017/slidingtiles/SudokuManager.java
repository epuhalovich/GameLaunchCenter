package fall2018.csc2017.slidingtiles;

import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Observable;

import fall2018.csc2017.slidingtiles.sudoku.SudokuGrid;

public class SudokuManager extends Observable implements GameManager,Serializable{
    public SudokuBoard sudokuBoard;
    private int score = 0;
    private Stack<Integer> undoPositionStack;
    private String numberToFill = "";


    public static SudokuManager getLevel(String level){
        if(level.equals("Easy")){
            return new SudokuManager(3);
        }
        else if(level.equals("Medium")){
            return new SudokuManager(5);
        }
        else{
            return new SudokuManager(7);
        }
    }

    public SudokuManager(int num){
        super();
        this.sudokuBoard = new SudokuBoard(num);
        this.undoPositionStack = new Stack<>();
    }

    public SudokuGrid[][] getPuzzle(){
        return sudokuBoard.puzzleSudoku;
    }

    public String getNumberToFill() {
        return this.numberToFill;
    }

    public Stack<Integer> getUndoPositionStack() {
        return undoPositionStack;
    }

    public void setNumberToFill(String numberToFill) {
        this.numberToFill = numberToFill;
    }

    public boolean getemptySpot(int x, int y){
        return ((this.getPuzzle())[x][y]).getNumber().equals("");
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean isGameOver() {
        return this.sudokuBoard.listSudoku == this.sudokuBoard.puzzleSudoku;
    }

    @Override
    public boolean isValidTap(int position) {
        int row = position / 9;
        int col = position % 9;
        return getemptySpot(row,col);
    }

    public void touchFill(int position) {
        int x = position / 9;
        int y = position % 9;
        ((this.getPuzzle())[x][y]).setNumber(this.numberToFill);
        this.undoPositionStack.push(x);
        this.undoPositionStack.push(y);
        this.numberToFill = "";
        score++;
        setChanged();
        notifyObservers();
    }

    public void tryUndo() {
        int y = undoPositionStack.pop();
        int x = undoPositionStack.pop();
        this.sudokuBoard.puzzleSudoku[x][y].setNumber("");
        score++;
        setChanged();
        notifyObservers();
    }

    public boolean checkRepeated(){
        return false;
    }
}