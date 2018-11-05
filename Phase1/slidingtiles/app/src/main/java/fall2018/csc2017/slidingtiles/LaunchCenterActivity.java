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
    }

    private void addChooseButtonListener() {
        Button chooseButton = findViewById(R.id.choosebutton);
        chooseButton.setOnClickListener(this::popup);
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

    public void setupLogOutListener(){
        Button signIn = findViewById(R.id.logout);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });
    }
}

