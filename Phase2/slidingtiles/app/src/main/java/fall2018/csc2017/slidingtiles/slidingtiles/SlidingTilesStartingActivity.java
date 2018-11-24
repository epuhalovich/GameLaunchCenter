package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.PopupMenu;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTilesStartingActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public static SlidingTilesScoreboard slidingTilesScoreboard;

    public static SlidingTilesController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Game MVC setup
        SlidingTilesFileSaver gameFileSaver = new SlidingTilesFileSaver(this, LogInActivity.currentPlayer.getGameFile());
        controller = new SlidingTilesController();
        if(gameFileSaver.getSlidingTilesManager() != null){
            controller.setSlidingTilesManager(gameFileSaver.getSlidingTilesManager());
        }
        controller.register(gameFileSaver);

        //Scoreboard MVC setup
        slidingTilesScoreboard = new SlidingTilesScoreboard();
        SlidingTilesScoreboardFileSaver scoreboardFileSaver = new SlidingTilesScoreboardFileSaver(this);
        slidingTilesScoreboard.register(scoreboardFileSaver);
        slidingTilesScoreboard.setGlobalScores(scoreboardFileSaver.globalScores);
        gameFileSaver.saveToFile();

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
                controller.setUpSlidingTiles("Easy");
                switchToGame();
                return true;

            case R.id.item2:
                controller.setUpSlidingTiles("Medium");
                switchToGame();
                return true;

            case R.id.item3:
                controller.setUpSlidingTiles("Hard");
                switchToGame();
                return true;

            default:
                return false;
        }
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(v -> {
            if (controller.getSlidingTilesManager() != null){
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
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        startActivity(tmp);
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
