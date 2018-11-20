package fall2018.csc2017.slidingtiles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuManager {

    private String arr[] = { "1","2","3","4","5","6","7","8","9" };
    private Set<String> correct = new HashSet<>(Arrays.asList(arr));


    // check whether the all rows contains 1-9.
    private boolean checkRows (ArrayList<ArrayList<String>> s) {
        for (int i = 0; i < 9; i++) {
            if (!checkRow(s.get(i))){
                return false;
            }
        }
        return true;
    }

    private void checkSquares (String[][] s){

    }
    private boolean checkColumns (ArrayList<ArrayList<String>> s){
        Set<String> c = new HashSet<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                c.add(s.get(i).get(j));
            }
            if (!(c == correct)){
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(ArrayList<String> row){
        Set<String> r = new HashSet<>();
        for (int i = 0; i < 9; i++){
            r.add(row.get(i));
        }
        return r == correct;
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
