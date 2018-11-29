package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import fall2018.csc2017.slidingtiles.memory.MemoryStartingActivity;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesStartingActivity;
import fall2018.csc2017.slidingtiles.sudoku.SudokuStartingActivity;

/**
 * The launch center activity.
 */
public class LaunchCenterActivity extends AppCompatActivity { //implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launchcenter);

        addGameButtonListeners();
        setupLogOutListener();

        // TODO: remove deprecated methods below
        //addChooseButtonListener();
    }

    /**
     * Activate the buttons for each game.
     */
    private void addGameButtonListeners() {
        ImageButton slidingTilesButton = findViewById(R.id.SlidingTilesImageButton);
        slidingTilesButton.setOnClickListener(v -> switchToGame("slidingtiles"));

        ImageButton sudokuButton = findViewById(R.id.SudokuImageButton);
        sudokuButton.setOnClickListener(v -> switchToGame("sudoku"));

        ImageButton memoryButton = findViewById(R.id.MemoryImageButton);
        memoryButton.setOnClickListener(v -> switchToGame("memory"));
    }

    public void switchToGame(String gameTitle){
        switch(gameTitle){
            case "slidingtiles":
                startActivity(new Intent(this, SlidingTilesStartingActivity.class));
                break;
            case "sudoku":
                startActivity(new Intent(this, SudokuStartingActivity.class));
                break;
            case "memory":
                startActivity(new Intent(this, MemoryStartingActivity.class));
        }
    }

    /**
     * Activate the logout button.
     */
    public void setupLogOutListener(){
        Button signIn = findViewById(R.id.logout);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });
    }
}

//    /**
//     * Activate the choose button.
//     */
//    private void addChooseButtonListener() {
//        Button chooseButton = findViewById(R.id.choosebutton);
//        chooseButton.setOnClickListener(this::popup);
//    }
//
//    /**
//     * Pop up a menu of games.
//     */
//    public void popup(View v) {
//        PopupMenu pop = new PopupMenu(this, v);
//        pop.setOnMenuItemClickListener(this);
//        pop.inflate(R.menu.game_menu);
//        pop.show();
//    }
//
//    /**
//     * Activate the items in the menu.
//     */
//    public boolean onMenuItemClick(MenuItem item){
//        switch(item.getItemId()){
//
//            case R.id.game1:
//                switchToSlidingTiles();
//                return true;
//
//            case R.id.game2:
//                switchToSudoku();
//                return true;
//
//            case R.id.game3:
//                switchToMemory();
//                return true;
//
//            default:
//                return false;
//        }
//    }
//
//    /**
//     * Switch to the sliding tile starting acticity.
//     */
//    private void switchToSlidingTiles() {
//        Intent tmp = new Intent(this, SlidingTilesStartingActivity.class);
//        startActivity(tmp);
//    }
//
//    /**
//     * Switch to the sudoku starting acticity.
//     */
//    private void switchToSudoku() {
//        Intent tmp = new Intent(this, SudokuStartingActivity.class);
//        startActivity(tmp);
//    }
//
//    /**
//     * Switch to the memory starting acticity.
//     */
//    private void switchToMemory() {
//        Intent tmp = new Intent(this, MemoryStartingActivity.class);
//        startActivity(tmp);
//    }

