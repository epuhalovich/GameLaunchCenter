package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.SudokuGameActivity;
import fall2018.csc2017.slidingtiles.SudokuManager;
import fall2018.csc2017.slidingtiles.SudokuStartingActivity;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;


public class MovementControl extends Observable {

    private SudokuManager sudokuManager = null;
    private int previousPosition =  0;
    private int previousBackground;
    private ArrayList<Integer> backgrounds = new ArrayList<>();
    private SudokuGameActivity gameActivity;
    private CustomAdapt customAdapt;
    public MovementControl() {
    }

    public void setSudokuManger(SudokuManager sudokuManger) {
        this.sudokuManager = sudokuManger;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if(backgrounds.size() == 0){ setUpBackgrounds();}
        setUpBackground(position);
        if (sudokuManager.getNumberToFill().equals("")) {
            Toast.makeText(context, "Please choose a number first ", Toast.LENGTH_SHORT).show();
        } else {
            if (sudokuManager.isValidTap(position)) {
                if (sudokuManager.checkRepeated(position)){
                    Toast.makeText(context, "Can't fill in this number(repeated)", Toast.LENGTH_SHORT).show();
                }
                else {
                    sudokuManager.touchMove(position);
                    if (sudokuManager.isGameOver()) {
                        Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(context, "Invalid Tap ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUpBackground(int position){
        int row = position / 9;
        int col = position % 9;
        setOriginal(sudokuManager.getPuzzle());
        setSelectedBackground(row,col,sudokuManager.getPuzzle());

        setChanged();
        notifyObservers();
    }

    public void setGameActivity(SudokuGameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    private void setOriginal(SudokuGrid[][] puzzle){
        for (int i = 0; i != 9; i++){
            for(int j = 0; j != 9; j++){
                puzzle[i][j].setBackground(backgrounds.get(i * 9 + j));
            }
        }
    }
    // set selected background and return the previous background
    private void setSelectedBackground(int row, int col, SudokuGrid[][] puzzle){
        setSelectedRow(row,puzzle);
        setSelectedColoumn(col,puzzle);
        setSelectedSquare(row,col,puzzle);
        sudokuManager.getPuzzle()[row][col].setBackground(R.drawable.button_selected);
        }

    private void setSelectedRow(int row, SudokuGrid[][] puzzle){
        for (SudokuGrid sudokuGrid : puzzle[row]){
            sudokuGrid.setBackground(R.drawable.button_pressed);
        }
    }

    private void setSelectedColoumn(int col, SudokuGrid[][] puzzle){
        for (int i = 0; i != 9; i++){
            puzzle[i][col].setBackground(R.drawable.button_pressed);
        }
    }

    private void setSelectedSquare(int row, int col, SudokuGrid[][] puzzle){
        int beginRow = (row/3) * 3;
        int beginCol = (col/3) * 3;
        for(int i = beginRow; i < (beginRow + 3); i++){
            for(int j = beginCol ;j < (beginCol + 3); j++){
                puzzle[i][j].setBackground(R.drawable.button_pressed);
            }

        }
    }

    private void setUpBackgrounds(){
        for (int i = 0; i != 9; i++){
            for(int j = 0; j != 9; j++){
                int background = sudokuManager.getPuzzle()[i][j].getBackground();
                backgrounds.add(background);

        }
        }
    }

    // set the background of the previous position back and set the new position to the button_pressed background
    private void resetBackground(int position, String mode){
        int row = position / 9;
        int col = position % 9;
        if (mode.equals("Original")){
            sudokuManager.getPuzzle()[row][col].setBackground(previousBackground);
        }
        else if (mode.equals("Pressed")){
            previousBackground = sudokuManager.getPuzzle()[row][col].getBackground();
           sudokuManager.getPuzzle()[row][col].setBackground(R.drawable.button_pressed);}
    }
}
