
package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.User;

/**
 * The score board display activity.
 */
public class ScoreBoardActivity extends AppCompatActivity {

    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer = LogInActivity.currentPlayer;

    /**
     *The controller of this view
     */
    private SlidingTilesScoreboard controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = SlidingTilesStartingActivity.slidingTilesScoreboard;
        setContentView(R.layout.activity_score_board);
        addReturnButtonListener();

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = controller.getScoreValues(false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = controller.getScoreValues(true, currentPlayer);
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


