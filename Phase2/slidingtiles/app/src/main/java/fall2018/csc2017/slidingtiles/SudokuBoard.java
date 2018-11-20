package fall2018.csc2017.slidingtiles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuBoard {
    private ArrayList<ArrayList<String>> newSudoku;
    private final String[][] seedSudoku = {
            {"1", "2","3","4","5","6","7","8","9"},
    {"4","5","6","7","8","9","1","2","3"},
        {"7","8","9","1","2","3","4","5","6"},
            {"2","1","4","3","6","5","8","9","7"},
                {"3","6","5","8","9","7","2","1","4"},
                    {"8","9","7","2","1","4","3","6","5"},
                        {"5","3","1","6","4","2","9","7","8"},
                            {"6","4","2","9","7","8","5","3","1"},
                                {"9","7","8","5","3","1","6","4","2"},
};
    private String[][] sample = seedSudoku;

    //create a new sudoku based on the sample by swapping two numbers.





    // create a new sudoku based on the sample by swapping rows and cols by 20 times.
    private String[][] getNewSudokuA(String[][] Sudoku){
        swap(Sudoku, "r");
        swap(Sudoku, "c");
       return Sudoku;
    }

    //swap two number of the seedSudoku.
    private void swapNumber(String[][] seed, String n, String m){

    }

    private void swap(String[][] seed, String ways){
        for (int i = 0; i < 21; i++){
            Random rand = new Random();
            int row = rand.nextInt(8);
            int col = rand.nextInt(8);
            if(ways.equals("r")){
                int changeRow = validChange(row);
                String a = seed[row][col];
                seed[row][col] = seed[changeRow][col];
                seed[changeRow][col] = a;}
            else{
                int changeCol = validChange(col);
                String a = seed[row][col];
                seed[row][col] = seed[row][changeCol];
                seed[row][changeCol] = a;

            }
        }
    }

    private int validChange(int i){
        Random rand = new Random();
        if (i % 3 == 0){
            return rand.nextInt(i + 2) + (i + 1);
        }
        else if ((i + 2) % 3 == 0){
            return rand.nextInt(i + 1) + (i - 1);
        }
        else{
            return rand.nextInt(i - 1) + (i - 2);
        }
    }

//    private ArrayList<ArrayList<String>> getSampleSudoku(){
//        ArrayList<ArrayList<String>> sample = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            List<String> a = Arrays.asList(seedSudoku[i]);
//            sample.add((ArrayList<String>) a);
//        }
//        return sample;
//    }
}