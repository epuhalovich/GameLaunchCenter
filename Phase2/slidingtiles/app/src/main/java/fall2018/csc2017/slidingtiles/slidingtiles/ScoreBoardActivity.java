
package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.Score;
import fall2018.csc2017.slidingtiles.User;

/**
 * The score board display activity.
 */
public class ScoreBoardActivity extends AppCompatActivity {

    /**
     * The scoreboard containing all the user and score data for sliding tiles.
     */
    private SlidingTilesScoreboard slidingTilesScoreboard;

    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer = LogInActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidingTilesScoreboard = new SlidingTilesScoreboard(this);
        ScoreboardSetup setup = new ScoreboardSetup(slidingTilesScoreboard, currentPlayer);
        setContentView(R.layout.activity_score_board);
        addReturnButtonListener();

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = setup.getScoreValues(false);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = setup.getScoreValues(true);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }
    private void switchToStarting(){
        Intent tmp = new Intent(this, SlidingTilesStartingActivity.class);
        startActivity(tmp);
    }
}


