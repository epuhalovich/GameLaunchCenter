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

public class SudokuController implements PhaseTwoSubject, GameController {
    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * the sudokuManager to control
     */
    private SudokuManager sudokuManager;

    /**
     * Create a new SudokuController
     */
    public SudokuController(){
        observers = new ArrayList<>();
    }

    /**
     * Return the sudoku manager being controlled
     * @return sudokuManager
     */
    public SudokuManager getGameManager(){
        return this.sudokuManager;
    }

    /**
     * Set the sudoku manager to be controlled
     * @param sudokuManager to be controlled
     */
    public void setGameManager(GameManager sudokuManager) {
        this.sudokuManager = (SudokuManager) sudokuManager;
    }

    /**
     * Set up board according to given level
     * @param level to set up board
     */
    public void setUpBoard(String level) {
        switch (level) {
            case "Easy":
                sudokuManager = new SudokuManager(3);
                break;
            case "Medium":
                sudokuManager = new SudokuManager(5);
                break;
            default:
                sudokuManager = new SudokuManager(7);
                break;
        }
        notifyObservers();
    }

    /**
     * Return true if the game is over and then add the games score to the scoreboard and reset
     * the sudoku manager to null.
     * @param scoreboard for sudoku
     * @param user name thats playing
     * @return true if score is ready to be added
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
     * Add an observer, obj, to this class
     * @param obj The observer to be added
     */
    public void register(PhaseTwoObserver obj){
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
