package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.PopupMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTilesStartingActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * The board manager.
     */
    private SlidingTilesManager slidingTilesManager;
    /**
     * The number of undos.
     */
    public static int NumUndos = 3;

    public static SlidingTilesScoreboard slidingTilesScoreboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidingTilesScoreboard = new SlidingTilesScoreboard();
        slidingTilesScoreboard.register(new SlidingTilesScoreboardFileSaver(this));
        saveToFile(TEMP_SAVE_FILENAME);

        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addViewScoreButtonListener();
        addSetUndoButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        //                slidingTilesManager = new SlidingTilesManager();
//                switchToGame();
        startButton.setOnClickListener(this::showPopup);
    }

    /**
     * Pop up a menu of complexities.
     */
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.complexity_menu);
        popup.show();
    }

    /**
     * Activate the items in the menu.
     */
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                setUpSlidingTiles("Easy");
                switchToGame();
                return true;

            case R.id.item2:
                setUpSlidingTiles("Medium");
                switchToGame();
                return true;

            case R.id.item3:
                setUpSlidingTiles("Hard");
                switchToGame();
                return true;

            default:
                return false;
        }
    }

    private void setUpSlidingTiles(String level) {
        slidingTilesManager = SlidingTilesManager.getLevel(level);
        slidingTilesManager.setNumUndos(NumUndos);
        saveToFile(LogInActivity.currentPlayer.getGameFile());
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(v -> {
            loadFromFile(LogInActivity.currentPlayer.getGameFile());
            if (slidingTilesManager != null){
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastLoadedText();
                switchToGame();
            }
            else{
                makeToastNoLoadedText();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    private void makeToastNoLoadedText() {
        Toast.makeText(this, "No Saved Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(TEMP_SAVE_FILENAME);
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        saveToFile(SlidingTilesStartingActivity.TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) throws NoSuchElementException {

        try {
            InputStream inputStream = this.openFileInput(fileName);
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

    /**
     * Activate the view score button.
     */
    private void addViewScoreButtonListener() {
        Button ScoreBoardButton = findViewById(R.id.ScoreBoardButton);
        ScoreBoardButton.setOnClickListener(v -> switchToScoreBoard());
    }

    /**
     * Switch to the ScoreBoardActivity view.
     */
    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        startActivity(tmp);
    }

    /**
     * Activate the setnumundo button.
     */
    private void addSetUndoButtonListener() {
        Button UndoButton = findViewById(R.id.SetundoButton);
        UndoButton.setOnClickListener(v -> switchToSetNumundo());
    }

    /**
     * Switch to the SlidingTilesSetNumundoActivity view.
     */
    private void switchToSetNumundo(){
        Intent tmp = new Intent(this, SlidingTilesSetNumundoActivity.class);
        startActivity(tmp);
    }
}
