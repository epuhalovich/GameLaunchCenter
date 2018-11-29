package fall2018.csc2017.slidingtiles.sudoku;


import java.io.Serializable;
import java.util.Stack;
import java.util.Observable;

import fall2018.csc2017.slidingtiles.GameManager;

public class SudokuManager extends Observable implements GameManager,Serializable{
    /**
     * A SudokuBoard
     */
    private SudokuBoard sudokuBoard;

    /**
     * The Score
     */
    private int score = 0;

    /**
     *  The Stack that restores all the undoPositions
     */
    private Stack<Integer> undoPositionStack;

    /**
     * The number to fill in the puzzleSudoku.
     */
    private String numberToFill = "";


    /**
     * Return a new SudokuManger depends on level.
     * @param level the difficulty of the game
     * @return the SudokuManger
     */
    public static SudokuManager getLevel(String level){
        switch (level) {
            case "Easy":
                return new SudokuManager(3);
            case "Medium":
                return new SudokuManager(5);
            default:
                return new SudokuManager(7);
        }
    }

    /**
     * Constrcutor for SudokuManger depends on the level.
     * @param num the level of Sudoku puzzle.
     */
    public SudokuManager(int num){
        super();
        this.sudokuBoard = new SudokuBoard(num);
        this.undoPositionStack = new Stack<>();
    }

    /**
     * Return a puzzle of Sodoku.
     * @return the puzzle of Sudoku
     */
    public SudokuGrid[][] getPuzzle(){
        return sudokuBoard.getPuzzleSudoku();
    }

    /**
     * Return the number the fill
     * @return int number to fill
     */
    public String getNumberToFill() {
        return this.numberToFill;
    }


    /**
     * Return the undoPositionStack
     */
    public Stack<Integer> getUndoPositionStack() {
        return undoPositionStack;
    }

    /**
     * Set the String number to fill
     * @param numberToFill
     */
    public void setNumberToFill(String numberToFill) {
        this.numberToFill = numberToFill;
    }

    /**
     * Return true iff get the empty spot with index int row x and int column y in the puzzles.
     * @param x the row
     * @param y the col
     */
    private boolean getEmptySpot(int x, int y){
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
                if (this.sudokuBoard.getPuzzleSudoku()[i][j].getNumber().equals(sudokuBoard.getSolutionSudoku()[i][j].getNumber())){
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
        return getEmptySpot(row,col);
    }

    /**
     * Make the move according to int position
     * @param position int position
     */
    public void touchMove(int position) {
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

    /**
     * Return the puzzle sudoku to the state one step before
     */
    public void tryUndo() {
        int y = undoPositionStack.pop();
        int x = undoPositionStack.pop();
        this.sudokuBoard.getPuzzleSudoku()[x][y].setNumber("");
        score++;
        setChanged();
        notifyObservers();
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the row
     * @param row the row selected
     */
    private boolean checkSelectedRow(int row){
        int wrongRow = 0;
        for (SudokuGrid sudokuGrid : this.getPuzzle()[row]){
            if (sudokuGrid.getNumber().equals(numberToFill)){
                wrongRow ++;
            }
        }
        return wrongRow != 0;
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the column
     * @param col the column selected
     */
    private boolean checkSelectedColumn(int col){
        int wrongCol = 0;
        for (int i = 0; i != 9; i++){
            if (this.getPuzzle()[i][col].getNumber().equals(numberToFill)){
                wrongCol ++;
            }
        }
        return wrongCol != 0;
    }

    /**
     * Return true iff there are no numbers equals to the number to fill in the corresponding square
     * @param row,col the row and column selected
     */
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

    /**
     * Return true iff the number to fill in can fill in the selected position.
     * @param position the position selected
     */
    public boolean checkRepeated(int position){
        int x = position / 9;
        int y = position % 9;
        return checkSelectedColumn(y) || checkSelectedRow(x) || checkSelectedSquare(x, y);
    }
}