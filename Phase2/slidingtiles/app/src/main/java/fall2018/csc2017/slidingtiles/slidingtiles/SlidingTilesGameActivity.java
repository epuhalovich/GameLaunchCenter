package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.CustomAdapter;
import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;
import fall2018.csc2017.slidingtiles.R;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends AppCompatActivity implements Observer, Serializable, PhaseTwoSubject{

    private List<PhaseTwoObserver> observers;
    /**
     * The board manager.
     */
    private SlidingTilesManager slidingTilesManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    private int NUM_COLS, NUM_ROWS;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFromFile();
        this.NUM_COLS = slidingTilesManager.getSlidingTilesBoard().NUM_COLS;
        this.NUM_ROWS = slidingTilesManager.getSlidingTilesBoard().NUM_ROWS;
        createTileButtons(this);
        setContentView(R.layout.activity_main);
        addUndoButtonListener();
        addSaveButtonListener();


        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(NUM_COLS);
        gridView.setSlidingTilesManager(slidingTilesManager);
        slidingTilesManager.getSlidingTilesBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / NUM_COLS;
                        columnHeight = displayHeight / NUM_ROWS;

                        display();
                    }
                });
    }

    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.UndoButton);
        undoButton.setOnClickListener(v -> {
            if (slidingTilesManager.getNumUndos() == 0){
                makeToastNoUndosText();
            }
            slidingTilesManager.tryUndo();
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(v -> {
            saveToFile(LogInActivity.currentPlayer.getGameFile());
            saveToFile(SlidingTilesStartingActivity.TEMP_SAVE_FILENAME);
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that there is no undo left to use.
     */
    private void makeToastNoUndosText() {
        Toast.makeText(this, "No undos left", Toast.LENGTH_SHORT).show();
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        SlidingTilesBoard slidingTilesBoard = slidingTilesManager.getSlidingTilesBoard();
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
    private void updateTileButtons() {
        SlidingTilesBoard slidingTilesBoard = slidingTilesManager.getSlidingTilesBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / NUM_ROWS;
            int col = nextPos % NUM_COLS;
            b.setBackgroundResource(slidingTilesBoard.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(SlidingTilesStartingActivity.TEMP_SAVE_FILENAME);
    }

    /**
     * Load the board manager from fileName.
     *
     */
    private void loadFromFile() {

        try {
            InputStream inputStream = this.openFileInput(SlidingTilesStartingActivity.TEMP_SAVE_FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                slidingTilesManager = (SlidingTilesManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(slidingTilesManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        saveToFile(LogInActivity.currentPlayer.getGameFile());
        if(slidingTilesManager.isGameOver()){
            SlidingTilesStartingActivity.slidingTilesScoreboard.addScore(LogInActivity.currentPlayer.getAccount(), slidingTilesManager.getScore());
        }
    }
    public void register(PhaseTwoObserver obj){

    }


    //method to notify observers of change
    public void notifyObservers(){

    }

}
