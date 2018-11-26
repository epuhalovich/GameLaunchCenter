package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.SudokuManager;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;


public class MovementControl {

    private SudokuManager sudokuManager = null;

    public MovementControl() {
    }

    public void setSudokuManger(SudokuManager sudokuManger) {
        this.sudokuManager = sudokuManger;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (sudokuManager.getNumberToFill().equals("")) {
            Toast.makeText(context, "Please choose a number ", Toast.LENGTH_SHORT).show();
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
}
