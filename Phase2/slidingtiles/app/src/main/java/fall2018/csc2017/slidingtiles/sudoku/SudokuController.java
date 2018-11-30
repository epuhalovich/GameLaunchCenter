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

    private SudokuManager sudokuManager;

    public SudokuController(){
        observers = new ArrayList<>();
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
