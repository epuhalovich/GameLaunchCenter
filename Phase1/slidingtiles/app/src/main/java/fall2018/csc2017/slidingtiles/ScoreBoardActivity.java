
package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ScoreBoardActivity extends AppCompatActivity {
    private SlidingTilesScoreboard slidingTilesScoreboard;
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

        String scoreValues = "";
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoresList.get(i);
            scoreValues = scoreValues + String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore()) + "\n";
        }
        return scoreValues;
    }

    private void addReturnButtonListener() {
        Button ReturnButton = (Button)findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { switchToStarting(); }
        });
    }
    private void switchToStarting(){
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
}


