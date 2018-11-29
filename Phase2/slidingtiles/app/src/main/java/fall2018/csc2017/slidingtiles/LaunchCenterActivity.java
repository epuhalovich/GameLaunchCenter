package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import fall2018.csc2017.slidingtiles.memory.MemoryStartingActivity;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesStartingActivity;
import fall2018.csc2017.slidingtiles.sudoku.SudokuStartingActivity;

/**
 * The launch center activity.
 */
public class LaunchCenterActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launchcenter);
        addChooseButtonListener();
    }

    /**
     * Activate the choose button.
     */
    private void addChooseButtonListener() {
        Button chooseButton = findViewById(R.id.choosebutton);
        chooseButton.setOnClickListener(this::popup);
        setupLogOutListener();
    }

    /**
     * Pop up a menu of games.
     */
    public void popup(View v) {
        PopupMenu pop = new PopupMenu(this, v);
        pop.setOnMenuItemClickListener(this);
        pop.inflate(R.menu.game_menu);
        pop.show();
    }

    /**
     * Activate the items in the menu.
     */
    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.game2:
                switchToSudoku();
                return true;

            case R.id.game1:
                switchToSlidingTile();
                return true;

            case R.id.game3:
                switchToMemory();
                return true;

            default:
                return false;
        }
    }

    /**
     * Switch to the sliding tile starting acticity.
     */
    private void switchToSlidingTile() {
        Intent tmp = new Intent(this, SlidingTilesStartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the sudoku starting acticity.
     */
    private void switchToSudoku() {
        Intent tmp = new Intent(this, SudokuStartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the memory starting acticity.
     */
    private void switchToMemory() {
        Intent tmp = new Intent(this, MemoryStartingActivity.class);
        startActivity(tmp);
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

