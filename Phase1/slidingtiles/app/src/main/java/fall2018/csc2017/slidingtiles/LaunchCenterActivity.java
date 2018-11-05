package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class LaunchCenterActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launchcenter);
        addChooseButtonListener();
        addViewScoreButtonListener();
    }

    private void addChooseButtonListener() {
        Button chooseButton = (Button)findViewById(R.id.choosebutton);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(v);
            }
        });
        setupLogOutListener();
    }

    public void popup(View v) {
        PopupMenu pop = new PopupMenu(this, v);
        pop.setOnMenuItemClickListener(this);
        pop.inflate(R.menu.game_menu);
        pop.show();
    }

    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.game1:
                switchToSlidingTile();
                return true;

            default:
                return false;
        }
    }

    private void switchToSlidingTile() {
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }

    private void addViewScoreButtonListener() {
        Button chooseButton = (Button)findViewById(R.id.viewscorebutton);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserScoreBoard();
            }
        });
    }
    private void switchToUserScoreBoard(){
        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
        startActivity(tmp);
    }

    public void setupLogOutListener(){
        Button signIn = findViewById(R.id.logout);
        LogInActivity.currentPlayer = null;
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });
    }
}
