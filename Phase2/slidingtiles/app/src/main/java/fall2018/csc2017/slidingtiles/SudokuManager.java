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
        int correct = 0;
        for (int i = 0; i != 9; i++){
            for (int j = 0; j != 9; j++){
                if (this.sudokuBoard.puzzleSudoku[i][j].getNumber().equals(sudokuBoard.listSudoku[i][j].getNumber())){
                    correct ++;
                }
            }
        }
        return correct == 81;
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

    private boolean checkSelectedRow(int row){
        int wrongRow = 0;
        for (SudokuGrid sudokuGrid : this.getPuzzle()[row]){
            if (sudokuGrid.getNumber().equals(numberToFill)){
                wrongRow ++;
            }
        }
        return wrongRow != 0;
    }

    private boolean checkSelectedColoumn(int col){
        int wrongCol = 0;
        for (int i = 0; i != 9; i++){
            if (this.getPuzzle()[i][col].getNumber().equals(numberToFill)){
                wrongCol ++;
            }
        }
        return wrongCol != 0;
    }

    private boolean checkSelectedSquare(int row, int col){
        int beginRow = (row/3) * 3;
        int beginCol = (col/3) * 3;
        int wrongSquare = 0;
        for(int i = beginRow; i < (beginRow + 3); i++){
            for(int j = beginCol ;j < (beginCol + 3); j++){
                if (this.getPuzzle()[i][j].getNumber().equals(numberToFill)){
                    wrongSquare ++;
                }
            }
        }
        return wrongSquare != 0;
    }

    public boolean checkRepeated(int position){
        int x = position / 9;
        int y = position % 9;
        return checkSelectedColoumn(y) || checkSelectedRow(x) || checkSelectedSquare(x, y);
    }
}