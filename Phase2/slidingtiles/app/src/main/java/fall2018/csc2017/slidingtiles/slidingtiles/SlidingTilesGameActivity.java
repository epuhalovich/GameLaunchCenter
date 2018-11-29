package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.CustomAdapter;
import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends AppCompatActivity implements Observer, Serializable{

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
        SlidingTilesStartingActivity.controller.updateTileButtons();
        gridView.setAdapter(new CustomAdapter(SlidingTilesStartingActivity.controller.getTileButtons(), columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.NUM_COLS = SlidingTilesStartingActivity.controller.getGameManager().getSlidingTilesBoard().NUM_COLS;
        this.NUM_ROWS = SlidingTilesStartingActivity.controller.getGameManager().getSlidingTilesBoard().NUM_ROWS;
        SlidingTilesStartingActivity.controller.createTileButtons(this);
        setContentView(R.layout.activity_main);
        addUndoButtonListener();
        addSaveButtonListener();


        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setmController(new SlidingTilesMovementController());
        gridView.setNumColumns(NUM_COLS);
        gridView.setGameManager(SlidingTilesStartingActivity.controller.getGameManager());
        SlidingTilesStartingActivity.controller.getGameManager().getSlidingTilesBoard().addObserver(this);
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
            if (SlidingTilesStartingActivity.controller.getGameManager().getNumUndos() == 0){
                makeToastNoUndosText();
            }
            SlidingTilesStartingActivity.controller.getGameManager().tryUndo();
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(v -> {
            SlidingTilesStartingActivity.controller.notifyObservers();
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
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        SlidingTilesStartingActivity.controller.notifyObservers();
    }


    @Override
    public void update(Observable o, Object arg) {
        display();
        SlidingTilesStartingActivity.controller.notifyObservers();
        if(SlidingTilesStartingActivity.controller.checkToAddScore(SlidingTilesStartingActivity.scoreboard, LogInActivity.currentPlayer.getAccount()))
        {switchToScoreBoard();}
        }

    /**
     * Switch to the ScoreBoard page.
     */
    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, SlidingTilesScoreBoardActivity.class);
        startActivity(tmp);
    }

}
