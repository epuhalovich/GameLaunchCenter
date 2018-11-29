package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;

import fall2018.csc2017.slidingtiles.GameManager;
import fall2018.csc2017.slidingtiles.MovementController;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.CustomAdapter;


public class SudokuMovementController extends Observable implements MovementController {


    private SudokuManager sudokuManager = null;

    public SudokuMovementController() {
    }

    public void setGameManager(GameManager sudokuManger) {
        this.sudokuManager = (SudokuManager) sudokuManger;
    }

    public void processTapMovement(Context context, int position, boolean display) {
//        if(sudokuManager.getBackgrounds().size() == 0){ sudokuManager.setUpBackgrounds();}
        sudokuManager.setUpBackground(position);
        if (sudokuManager.getNumberToFill().equals("")) {
            Toast.makeText(context, "Choose a number", Toast.LENGTH_SHORT).show();
        } else {
            if (sudokuManager.isValidTap(position)) {
                if (sudokuManager.checkRepeated(position)){
                    Toast.makeText(context, "There is repeated number!", Toast.LENGTH_SHORT).show();
                }
                else {
                    sudokuManager.touchMove(position);
                    if (sudokuManager.isGameOver()) {
                        Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
            }
        }
        sudokuManager.setOriginal(sudokuManager.getPuzzle());
    }

//    private void setUpBackground(int position){
//        int row = position / 9;
//        int col = position % 9;
//        setSelectedBackground(row,col,sudokuManager.getPuzzle());
//        setChanged();
//        notifyObservers();
//    }
//
//
//    private void setOriginal(SudokuGrid[][] puzzle){
//        for (int i = 0; i != 9; i++){
//            for(int j = 0; j != 9; j++){
//                puzzle[i][j].setBackground(backgrounds.get(i * 9 + j));
//            }
//        }
//    }
//    // set selected background and return the previous background
//    private void setSelectedBackground(int row, int col, SudokuGrid[][] puzzle){
//        setSelectedRow(row,puzzle);
//        setSelectedColoumn(col,puzzle);
//        setSelectedSquare(row,col,puzzle);
//        sudokuManager.getPuzzle()[row][col].setBackground(R.drawable.button_selected);
//        }
//
//    private void setSelectedRow(int row, SudokuGrid[][] puzzle){
//        for (SudokuGrid sudokuGrid : puzzle[row]){
//            sudokuGrid.setBackground(R.drawable.button_pressed);
//        }
//    }
//
//    private void setSelectedColoumn(int col, SudokuGrid[][] puzzle){
//        for (int i = 0; i != 9; i++){
//            puzzle[i][col].setBackground(R.drawable.button_pressed);
//        }
//    }
//
//    private void setSelectedSquare(int row, int col, SudokuGrid[][] puzzle){
//        int beginRow = (row/3) * 3;
//        int beginCol = (col/3) * 3;
//        for(int i = beginRow; i < (beginRow + 3); i++){
//            for(int j = beginCol ;j < (beginCol + 3); j++){
//                puzzle[i][j].setBackground(R.drawable.button_pressed);
//            }
//
//        }
//    }
//
//    private void setUpBackgrounds(){
//        for (int i = 0; i != 9; i++){
//            for(int j = 0; j != 9; j++){
//                int background = sudokuManager.getPuzzle()[i][j].getBackground();
//                backgrounds.add(background);
//
//        }
//        }
//    }
}
