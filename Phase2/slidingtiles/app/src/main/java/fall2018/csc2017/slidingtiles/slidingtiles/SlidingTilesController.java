package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;




public class SlidingTilesController implements PhaseTwoSubject {


    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    private int NumUndos;

    private static List<PhaseTwoObserver> observers;

    private SlidingTilesManager slidingTilesManager;

    public SlidingTilesController (){
        observers = new ArrayList<>();
    }

    public void setNumUndos(int numUndos) {
        NumUndos = numUndos;
    }

    public SlidingTilesManager getSlidingTilesManager() {
        return slidingTilesManager;
    }

    public void setSlidingTilesManager(SlidingTilesManager slidingTilesManager) {
        this.slidingTilesManager = slidingTilesManager;
    }

    public void setUpSlidingTiles(String level) {
        slidingTilesManager = SlidingTilesManager.getLevel(level);
        slidingTilesManager.setNumUndos(NumUndos);
        notifyObservers();
    }

    public ArrayList<Button> getTileButtons() {
        return tileButtons;
    }

    /**
     * Create the buttons for displaying the tiles.
     *
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


    void checkToAddScore(SlidingTilesScoreboard slidingTilesScoreboard, String user) {
        if (slidingTilesManager.isGameOver()) {
            slidingTilesScoreboard.addScore(user, slidingTilesManager.getScore());
        }
    }

    public void register(PhaseTwoObserver obj){
        if(obj == null) throw new NullPointerException("Null Observer");
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }

    public void notifyObservers(){
        for (PhaseTwoObserver obj : observers) {
            obj.update();
        }
    }
}
