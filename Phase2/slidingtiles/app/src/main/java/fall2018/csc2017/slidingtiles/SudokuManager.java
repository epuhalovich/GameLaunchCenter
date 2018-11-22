package fall2018.csc2017.slidingtiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuManager {

    private String arr[] = { "1","2","3","4","5","6","7","8","9" };
    private Set<String> correct = new HashSet<>(Arrays.asList(arr));



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

}