package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.GameController;
import fall2018.csc2017.slidingtiles.GameManager;
import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;
import fall2018.csc2017.slidingtiles.Scoreboard;
import fall2018.csc2017.slidingtiles.sudoku.SudokuGrid;
import fall2018.csc2017.slidingtiles.sudoku.SudokuManager;

/**
 * Control the view and model of a Sudoku game.
 */
class SudokuController implements PhaseTwoSubject, GameController {
    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> BoxButtons;

    /**
     * The Sudoku that is being controlled
     */
    private SudokuManager sudokuManager;

    /**
     * Create a new SudokuController
     */
    public SudokuController() {
        observers = new ArrayList<>();
    }

    /**
     * Get the BoxButtons.
     */
    public ArrayList<Button> getBoxButtons() {
        return BoxButtons;
    }

    /**
     * Get the sudokuManager
     */
    public SudokuManager getGameManager() {
        return this.sudokuManager;
    }

    /**
     * Set a new slidingTilesManager for this class
     *
     * @param sudokuManager
     */
    public void setGameManager(GameManager sudokuManager) {
        this.sudokuManager = (SudokuManager) sudokuManager;
    }

    /**
     * Set up a slidingtiles game in accordance with the selected level
     *
     * @param level the level the user has selected
     */
    public void setUpBoard(String level) {
        sudokuManager = SudokuManager.getLevel(level);
        notifyObservers();
    }

    /**
     * Add a score to the scoreboard iff the game is finished
     *
     * @param scoreboard
     * @param user
     */
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (sudokuManager.isGameOver()) {
            scoreboard.addScore(user, sudokuManager.getScore());
            sudokuManager = null;
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    public void createTileButtons(Context context) {
        SudokuGrid[][] sudokuPuzzle = sudokuManager.getPuzzle();
        BoxButtons = new ArrayList<>();
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(sudokuPuzzle[row][col].getBackground());
                this.BoxButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    public void updateTileButtons() {
        SudokuGrid[][] sudokuBoard = sudokuManager.getPuzzle();
        int buttonPosition = 0;
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                Button b = BoxButtons.get(buttonPosition);
                b.setText(sudokuBoard[row][col].getNumber());
                b.setTextSize(17);
                b.setBackgroundResource(sudokuBoard[row][col].getBackground());
                buttonPosition++;
            }
        }
    }

    /**
     * Add an observer, obj, to this class
     *
     * @param obj The observer to be added
     */
    public void register(PhaseTwoObserver obj) {
        if (obj == null) throw new NullPointerException("Null Observer");
        if (!observers.contains(obj)) {
            observers.add(obj);
            obj.setSubject(this);
        }
    }

    /**
     * Update the observers of this class
     */
    public void notifyObservers() {
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }
}
