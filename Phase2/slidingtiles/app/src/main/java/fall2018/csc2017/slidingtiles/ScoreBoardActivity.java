
package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

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
        setContentView(R.layout.activity_score_board);
        addReturnButtonListener();

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = getScoreValues(false);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = getScoreValues(true);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Checks the Sliding Tiles scoreboard for the top scores.
     * If userScoresOnly is true, only looks up scores for the current player.
     * Returns a string of the top 5 (or less if less than 5 exist) scores for this game.
     *
     * @param userScoresOnly true when looking only for current player's scores
     * @return scoreValues
     */
    private String getScoreValues(boolean userScoresOnly) {

        ArrayList<Score> scoresList;
        int numScores;

        if (userScoresOnly) {
            scoresList = slidingTilesScoreboard.getUserScoreboard(currentPlayer);
        }
        else {
            scoresList = slidingTilesScoreboard.getGlobalScoreboard();
        }

        if (scoresList.size() < 5) {
            numScores = scoresList.size();
        }
        else {
            numScores = 5;
        }

        StringBuilder scoreValues = new StringBuilder();
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoresList.get(i);
            scoreValues.append(String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore())).append("\n");
        }
        return scoreValues.toString();
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }
    private void switchToStarting(){
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
}


