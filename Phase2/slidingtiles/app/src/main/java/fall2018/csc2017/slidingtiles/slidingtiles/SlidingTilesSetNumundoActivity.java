package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesStartingActivity;

public class SlidingTilesSetNumundoActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_numundo);
        addChooseButtonListener();
    }

    /**
     * Activate the choose button.
     */
    private void addChooseButtonListener() {
        Button chooseButton = findViewById(R.id.ChooseButton);
        chooseButton.setOnClickListener(this::popup);
    }

    /**
     * Pop up a menu of numbers.
     */
    public void popup(View v) {
        PopupMenu pop = new PopupMenu(this, v);
        pop.setOnMenuItemClickListener(this);
        pop.inflate(R.menu.number_menu);
        pop.show();
    }

    /**
     * Activate the items in the menu.
     */
    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.i5:
                SlidingTilesStartingActivity.NumUndos = 5;
                makeToastText();
                switchToStartingActivity();
                return true;

            case R.id.i4:
                SlidingTilesStartingActivity.NumUndos = 4;
                makeToastText();
                switchToStartingActivity();
                return true;

            case R.id.i3:
                SlidingTilesStartingActivity.NumUndos = 3;
                makeToastText();
                switchToStartingActivity();
                return true;

            case R.id.i2:
                SlidingTilesStartingActivity.NumUndos = 2;
                makeToastText();
                switchToStartingActivity();
                return true;

            case R.id.i1:
                SlidingTilesStartingActivity.NumUndos = 1;
                makeToastText();
                switchToStartingActivity();
                return true;

            case R.id.i0:
                SlidingTilesStartingActivity.NumUndos = 0;
                makeToastText();
                switchToStartingActivity();
                return true;

            default:
                return false;
        }
    }

    /**
     * Switch to the SlidingTilesStartingActivity view.
     */
    private void switchToStartingActivity(){
        Intent tmp = new Intent(this, SlidingTilesStartingActivity.class);
        startActivity(tmp);
    }

    /**
     * Display that user has set numundos successfully.
     */
    private void makeToastText() {
        Toast.makeText(this, "Successful!", Toast.LENGTH_LONG).show();
    }
}
