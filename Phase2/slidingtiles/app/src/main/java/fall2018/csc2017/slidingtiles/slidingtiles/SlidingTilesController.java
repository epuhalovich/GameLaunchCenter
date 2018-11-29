package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.GameController;
import fall2018.csc2017.slidingtiles.GameManager;
import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;
import fall2018.csc2017.slidingtiles.Scoreboard;


/**
 * Control the view and model of a Sliding tiles game.
 */
public class SlidingTilesController implements PhaseTwoSubject, GameController {


    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The amount of undos in a sliding tiles game
     */
    private int NumUndos = 3;

    /**
     * The list of observers of this class
     */
    private static List<PhaseTwoObserver> observers;

    /**
     * The SlidingTilesManager that is being controlled
     */
    private SlidingTilesManager slidingTilesManager;

    /**
     * Create a new SlidingTilesController
     */
    public SlidingTilesController (){
        observers = new ArrayList<>();
    }

    /**
     * Set the amount of undos available in the slidingtiles game
     * @param numUndos the amount of undos
     */
    public void setNumUndos(int numUndos) {
        NumUndos = numUndos;
    }

    /**
     * Return the manager for sliding tiles
     * @return SlidingTilesManager
     */
    public SlidingTilesManager getGameManager() {
        return slidingTilesManager;
    }

    /**
     * Set a new slidingTilesManager for this class
     * @param slidingTilesManager
     */
    public void setGameManager(GameManager slidingTilesManager) {
        this.slidingTilesManager = (SlidingTilesManager) slidingTilesManager;
    }

    /**
     * Set up a slidingtiles game in accordance with the selected level
     * @param level the level the user has selected
     */
    public void setUpBoard(String level) {
        slidingTilesManager = SlidingTilesManager.getLevel(level);
        slidingTilesManager.setNumUndos(NumUndos);
        notifyObservers();
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
     * @param context the context
     */
    public void createTileButtons(Context context) {
        SlidingTilesBoard slidingTilesBoard = slidingTilesManager.getSlidingTilesBoard();
        int NUM_ROWS = slidingTilesBoard.NUM_ROWS;
        int NUM_COLS = slidingTilesBoard.NUM_COLS;
        tileButtons = new ArrayList<>();
        for (int row = 0; row != NUM_ROWS; row++) {
            for (int col = 0; col != NUM_COLS; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(slidingTilesBoard.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    public void updateTileButtons() {
        SlidingTilesBoard slidingTilesBoard = slidingTilesManager.getSlidingTilesBoard();
        int nextPos = 0;
        int NUM_ROWS = slidingTilesBoard.NUM_ROWS;
        int NUM_COLS = slidingTilesBoard.NUM_COLS;
        for (Button b : tileButtons) {
            int row = nextPos / NUM_ROWS;
            int col = nextPos % NUM_COLS;
            b.setBackgroundResource(slidingTilesBoard.getTile(row, col).getBackground());
            nextPos++;
        }
    }


    /**
     * Add a score to the scoreboard iff the game is finished
     * @param scoreboard
     * @param user
     */
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if (slidingTilesManager.isGameOver()) {
            scoreboard.addScore(user, slidingTilesManager.getScore());
            slidingTilesManager = null;
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
