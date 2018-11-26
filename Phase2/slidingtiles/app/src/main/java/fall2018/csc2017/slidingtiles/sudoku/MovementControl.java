package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.SudokuManager;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;


public class MovementControl {

    private SudokuManager sudokuManager = null;
    private int previousPosition =  0;
    private int currectPosition = 0;

    public MovementControl() {
    }

    public void setSudokuManger(SudokuManager sudokuManger) {
        this.sudokuManager = sudokuManger;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (sudokuManager.getNumberToFill().equals("")) {
            Toast.makeText(context, "Please choose a number first ", Toast.LENGTH_SHORT).show();
        } else {
            if (sudokuManager.isValidTap(position)) {
                sudokuManager.touchFill(position);
                if (sudokuManager.isGameOver()) {
                    Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Tap ", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void resetBackground(int position, String mode){
        int row = position / 9;
        int col = position % 9;
        if (mode.equals("Original")){
            sudokuManager.getPuzzle()[row][col].setBackground(R.drawable.custom_button);
        }
        else if (mode.equals("Pressed")){
           sudokuManager.getPuzzle()[row][col].setBackground(R.drawable.button_pressed);}
    }


}
