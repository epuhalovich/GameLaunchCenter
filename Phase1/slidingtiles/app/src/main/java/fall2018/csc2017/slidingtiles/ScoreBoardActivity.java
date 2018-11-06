
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
    private User player = LogInActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        addReturnButtonListener();

        SlidingTilesScoreboard.addScore("npnp", 45);
        SlidingTilesScoreboard.addScore("npnp", 103);

        SlidingTilesScoreboard.addScore("bhbhj", 22);
        SlidingTilesScoreboard.addScore("bhbhj", 82);

        // HERE WE GET THE GLOBAL TOP SCORES

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        ArrayList<Score> globalScoresList = SlidingTilesScoreboard.getGlobalScoreboard();
        int numGlobalScores;

        if (globalScoresList.size() < 5) {
            numGlobalScores = globalScoresList.size();
        }
        else {
            numGlobalScores = 5;
        }

        String globalScoreValues = "";
        for (int i = 0; i < numGlobalScores; i++) {
            Score currentItem = globalScoresList.get(i);
            globalScoreValues = globalScoreValues + String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore()) + "\n";
        }
        globalScoresText.setText(globalScoreValues);

// NOW WE GET THE USER TOP SCORES

        TextView userScoresText = findViewById(R.id.UserScores);
        ArrayList<Score> userScoresList = SlidingTilesScoreboard.getUserScoreboard(LogInActivity.currentPlayer);
        int numUserScores;

        if (userScoresList.size() < 5) {
            numUserScores = userScoresList.size();
        }
        else {
            numUserScores = 5;
        }

        String userScoreValues = "";
        for (int i = 0; i < numUserScores; i++) {
            Score currentItem = userScoresList.get(i);
            userScoreValues = userScoreValues + String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore()) + "\n";
        }
        userScoresText.setText(userScoreValues);
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


