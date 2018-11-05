
package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ScoreBoardActivity extends AppCompatActivity {
    private User player = LogInActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        addReturnButtonListener();

        TextView globalScores = (TextView) findViewById(R.id.GlobalScores); // TODO: make this actually show GLOBAL SCORES
        globalScores.setText(String.format(Locale.US, "%d moves", player.getHeighest_score()));

        TextView userScores = (TextView) findViewById(R.id.UserScores); // TODO: make this actually show USER SCORES
        userScores.setText(String.format(Locale.US, "%d moves", player.getHeighest_score()));
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


