package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuBoard implements Serializable {
    private Random rand = new Random();
    public  List<List<String>> listSudoku;
    public List<List<String>> puzzleSudoku;
    private final String[][][] seedSudokus = {{
            {"1", "2","3","4","5","6","7","8","9"},
            {"4","5","6","7","8","9","1","2","3"},
            {"7","8","9","1","2","3","4","5","6"},
            {"2","1","4","3","6","5","8","9","7"},
            {"3","6","5","8","9","7","2","1","4"},
            {"8","9","7","2","1","4","3","6","5"},
            {"5","3","1","6","4","2","9","7","8"},
            {"6","4","2","9","7","8","5","3","1"},
            {"9","7","8","5","3","1","6","4","2"}},
            {{"3","9","4","5","1","7","6","2","8"},
                    { "5", "1", "7", "6", "2", "8", "3", "9", "4" },
                    { "6", "2", "8", "3", "9", "4", "5", "1", "7" },
                    { "9", "3", "5", "4", "7", "1", "2", "8", "6" },
                    { "4", "7", "1", "2", "8", "6", "9", "3", "5" },
                    { "2", "8", "6", "9", "3", "5", "4", "7", "1" },
                    { "1", "4", "3", "7", "5", "9", "8", "6", "2" },
                    { "7", "5", "9", "8", "6", "2", "1", "4", "3" },
                    { "8", "6", "2", "1", "4", "3", "7", "5", "9" }}};


    public SudokuBoard(int level){
        this.listSudoku = getListSampleSudoku(getNewSudoku());
        this.puzzleSudoku = createPuzzle(level, this.listSudoku);
    }

    // create a new sudoku based on the sample by swapping rows and cols by 20 times.
    private String[][] getNewSudoku(){
        ArrayList<String[][]> matrixs = new ArrayList<>();
        String[][] sample = getSampleSudoku();
        for(int times = 0; times != 10; times++){
            matrixs.add(swapColumn(sample));
            matrixs.add(swapDia(sample));
            matrixs.add(swapLine(sample));
            matrixs.add(swapNumber(sample));
        }
        return matrixs.get(rand.nextInt(matrixs.size()));
    }


    //swap two number in the diagnal
    private String[][] swapDia(String[][] sample) {
        String[][] dia = new String[9][9];
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != 9; j++) {
                dia[i][j] = sample[j][i];
            }
        }
        return dia;
    }
    //swap two number of the seedSudoku.
    private String[][] swapNumber(String[][] sample){
        for(int times = 0; times != 8; times ++){
            String changeNum = Integer.toString(rand.nextInt(9) + 1);
            String Num = Integer.toString(rand.nextInt(9) + 1);
            for(int i = 0; i != 9; i++){
                for(int j = 0; j!=9; j++){
                    if(sample[i][j].equals(Num)){
                        sample[i][j] = changeNum;
                    }
                    else if(sample[i][j].equals(changeNum)){
                        sample[i][j] = Num;
                    }
                }
            }
        }
        return sample;
    }

    //swap the number in the valid row
    private String[][] swapLine(String[][] sample){
        for (int i = 0; i != 5; i++){
            int row = rand.nextInt(9);
            int changeRow = validChange(row);
            String[] a = sample[row];
            sample[row] = sample[changeRow];
            sample[changeRow] = a;
        }
        return sample;
    }

    //swap the number in the valid column
    private String[][] swapColumn(String[][] sample){
        for (int i = 0; i != 5; i++){
            int col = rand.nextInt(9);
            int changeCol = validChange(col);
            for(int row = 0; row != 9; row ++){
                String a = sample[row][col];
                sample[row][col] = sample[row][changeCol];
                sample[row][changeCol] = a;
            }
        }
        return sample;
    }


    // return the changable cols and rows
    private int validChange(int i){
        if (i % 3 == 0){
            return rand.nextInt(2) + (i + 1);
        }
        else if ((i + 2) % 3 == 0){
            return rand.nextInt(3) + (i - 1);
        }
        else{
            return rand.nextInt(2) + (i - 2);
        }
    }

    // Randomly clone a sudoku in seedsudokus
    private String[][] getSampleSudoku(){
        int random = rand.nextInt(seedSudokus.length);
        return seedSudokus[random].clone();
    }

    // change a String[][] to a list
    public static List<List<String>> getListSampleSudoku(String[][] clone){
        List<List<String>> sample = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<String> a = Arrays.asList(clone[i]);
            sample.add(a);
        }
        return sample;
    }

    /**
     * make the implemented sudoku board a puzzle.
     * @param num the number of boxes that are empty.
     */

    public List<List<String>> createPuzzle(int num, List<List<String>> solution) {
        List<List<String>> puzzle = new ArrayList<>(solution);
        for (int i = 0; i != 9; i++) {
            for (int j = 0; j != num; j++){
                int x = rand.nextInt(9);
                puzzle.get(i).set(x, "");
            }
        }
        return puzzle;
    }
}


