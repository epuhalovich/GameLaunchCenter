package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UserScoreBoardActivity extends AppCompatActivity {
    private User player = LogInActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score_board);
        TextView textView = (TextView) findViewById(R.id.STscore);
        textView.setText(player.getHeighest_score() + " moves");
    }
}

