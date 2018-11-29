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
        if (!(sudokuManager == null)) {
            sudokuManager.setUpBackground(position);
            if (sudokuManager.getNumberToFill().equals("")) {
                Toast.makeText(context, "Choose a number", Toast.LENGTH_SHORT).show();
            } else {
                if (sudokuManager.isValidTap(position)) {
                    if (sudokuManager.checkRepeated(position)) {
                        Toast.makeText(context, "There is repeated number!", Toast.LENGTH_SHORT).show();
                    } else {
                        sudokuManager.touchMove(position);
                        if (sudokuManager.isGameOver()) {
                            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
                }
            }
            sudokuManager.setOriginal(sudokuManager.getSudokuBoard().getPuzzleSudoku());
        }
    }
}
