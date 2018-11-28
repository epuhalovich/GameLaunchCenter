package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.sudoku.SudokuGrid;

class SudokuController implements PhaseTwoSubject, GameController {
    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;
    private ArrayList<Button> BoxButtons;

    private SudokuManager sudokuManager;

    public SudokuController(){
        observers = new ArrayList<>();
    }

    public ArrayList<Button> getBoxButtons(){
        return BoxButtons;
    }

    public SudokuManager getGameManager(){
        return this.sudokuManager;
    }

    public void setGameManager(GameManager sudokuManager) {
        this.sudokuManager = (SudokuManager) sudokuManager;
    }

    public void setUpBoard(String level) {
        sudokuManager = SudokuManager.getLevel(level);
        notifyObservers();
    }

    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (sudokuManager.isGameOver()) {
            scoreboard.addScore(user, sudokuManager.getScore());
            sudokuManager = null;
            notifyObservers();
            return true;
        }
        return false;
    }

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

    public void updateTileButtons() {
        SudokuGrid[][] sudokuBoard = sudokuManager.getPuzzle();
        int buttonPosition = 0;
        for(int row = 0; row != 9; row ++){
            for(int col = 0; col != 9; col++){
                Button b = BoxButtons.get(buttonPosition);
                b.setText(sudokuBoard[row][col].getNumber());
                b.setTextSize(17);
                b.setBackgroundResource(sudokuBoard[row][col].getBackground());
                buttonPosition ++;
            }
        }
    }
    /**
     * Add an observer, obj, to this class
     * @param obj The observer to be added
     */
    public void register(PhaseTwoObserver obj){
        if(obj == null) throw new NullPointerException("Null Observer");
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }

    /**
     * Update the observers of this class
     */
    public void notifyObservers(){
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }
}
