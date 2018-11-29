package fall2018.csc2017.slidingtiles.memory;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.GameController;
import fall2018.csc2017.slidingtiles.GameManager;
import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;
import fall2018.csc2017.slidingtiles.Scoreboard;

class MemoryController implements GameController, PhaseTwoSubject {

    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;


    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The SlidingTilesManager that is being controlled
     */
    private MemoryManager memoryManager;

    /**
     * Create a new SlidingTilesController
     */
    public MemoryController(){
        observers = new ArrayList<>();
    }

    public MemoryManager getGameManager() {
        return memoryManager;
    }

    public void setGameManager(GameManager manager) {
        this.memoryManager = (MemoryManager) manager;
    }

    public void setUpBoard(String level) {
        memoryManager = MemoryManager.getLevel(level);
        notifyObservers();
    }
    /**
     * Add a score to the scoreboard iff the game is finished
     * @param scoreboard
     * @param user
     */
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (memoryManager.isGameOver()) {
            scoreboard.addScore(user, memoryManager.getScore());
            memoryManager = null;
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

    /**
     * Return the array of tile buttons
     * @return ArrayList</Button>
     */
    public ArrayList<Button> getTileButtons() {
        return tileButtons;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    public void createTileButtons(Context context) {
        MemoryBoard memoryBoard = memoryManager.getMemoryBoard();
        int NUM_ROWS = memoryBoard.getNUM_ROWS();
        int NUM_COLS = memoryBoard.getNUM_COLS();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != NUM_ROWS; row++) {
            for (int col = 0; col != NUM_COLS; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(memoryBoard.getPairs(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    public void updateTileButtons() {
        MemoryBoard memoryBoard = memoryManager.getMemoryBoard();
        int nextPos = 0;
        int NUM_ROWS = memoryBoard.getNUM_ROWS();
        int NUM_COLS = memoryBoard.getNUM_COLS();
        for (Button b : tileButtons) {
            int row = nextPos / NUM_ROWS;
            int col = nextPos % NUM_COLS;
            b.setBackgroundResource(memoryBoard.getPairs(row, col).getBackground());
            nextPos++;
        }
    }




}
