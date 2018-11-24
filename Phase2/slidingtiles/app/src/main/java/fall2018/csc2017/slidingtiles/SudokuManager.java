package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuManager implements GameManager, Serializable{
    private String arr[] = { "1","2","3","4","5","6","7","8","9" };
    private Set<String> correct = new HashSet<>(Arrays.asList(arr));
    private SudokuBoard sudokuBoard;
//    private List<List<String>> puzzle = sudokuBoard.listSudoku;
//    private List<List<String>> solution = sudokuBoard.puzzleSudoku;


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
    }

    public List<List<String>> getPuzzle(){
        return sudokuBoard.puzzleSudoku;
    }

    public List<List<String>> getSolultion(){
        return sudokuBoard.listSudoku;
    }


    public void setNumber(int row, int col, String number){
        sudokuBoard.puzzleSudoku.get(row).set(col, number);
    }

    public void clearNumber(int row, int col){
        sudokuBoard.puzzleSudoku.get(row).set(col, "");
    }

    public boolean checkSudoku(List<List<String>> s){
        return checkSquares(s) && (checkColumns(s) && checkRows(s));
    }

    // check whether the all rows contains 1-9.
    private boolean checkRows (List<List<String>> s) {
        for (int i = 0; i < 9; i++) {
            if (!checkRow(s.get(i))){
                return false;
            }
        }
        return true;
    }


    private boolean checkSquares (List<List<String>> s){
        for(int row = 0; row != 9; row += 3){
            for(int col = 0; col != 9; col += 3){
                if (!(checkSquare(row,col,s))){
                    System.out.println("Square false");
                    return false;

                }
            }
        }
        return true;
    }
    private boolean checkSquare (int x, int y, List<List<String>> s){
        Set<String> number = new HashSet<>();
        for(int i = x;  i != x + 3; i++){
            for (int j = y; j != y + 3; j++){
                number.add(s.get(i).get(j));
            }
        }
        return number.equals(correct);
    }
    private boolean checkColumns (List<List<String>> s){
        Set<String> c = new HashSet<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                c.add(s.get(i).get(j));
            }
            if (!(c.equals(correct))){
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(List<String> row){
        Set<String> r = new HashSet<>();
        for (int i = 0; i < 9; i++){
            r.add(row.get(i));
        }
        return r.equals(correct);
    }
    // return the rows or columns depends on the position of view of x or y
    private int changeToIndex(float viewX, float boxSide){
        int index = -1;
        for(int i = 0; i!= 9; i++){
            if ((20 + i * boxSide < viewX) && (20 + (i + 1) * boxSide > viewX)) {
                index = i;
            }
        }
        return index;
    }

    public  boolean getemptySpot(int x, int y){
        return this.getPuzzle().get(x).get(y).equals("");
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isValidTap(int Position) {
        return false;
    }

    @Override
    public void touchMove(int Position) {
    }
}