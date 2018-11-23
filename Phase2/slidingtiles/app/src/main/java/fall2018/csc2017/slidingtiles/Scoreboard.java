package fall2018.csc2017.slidingtiles;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public abstract class Scoreboard implements Serializable {


    public ArrayList<Score> globalScores;


    public Scoreboard(){}

    public ArrayList<Score> getGlobalScores() {
         return globalScores;
    }

    public abstract void addScore(String currentPlayerId, int score);



    public abstract ArrayList<Score> sortScores(ArrayList<Score> scores);

    public void setGlobalScores(ArrayList<Score> globalScores) {
        this.globalScores = globalScores;
    }
    /**
     * Checks the Sliding Tiles scoreboard for the top scores.
     * If userScoresOnly is true, only looks up scores for the current player.
     * Returns a string of the top 5 (or less if less than 5 exist) scores for this game.
     *
     * @param userScoresOnly true when looking only for current player's scores
     * @return scoreValues
     */
    public String getScoreValues(boolean userScoresOnly, User currentPlayer ) {

        ArrayList<Score> scoresList;
        int numScores;

        if (userScoresOnly) {
            scoresList = getUserScoreboard(currentPlayer);
        }
        else {
            scoresList = globalScores;
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
     * Returns an ArrayList representing the global scoreboard for sliding tiles game.
     * @return globalScores
     */
    public ArrayList<Score> getGlobalScoreboard(){
        return globalScores;
    }

    /**
     * Returns an ArrayList representing the users scoreboard for sliding tiles game.
     * @return UserScores
     */
    public ArrayList<Score> getUserScoreboard(User current_player){
        ArrayList<Score> UserScores = new ArrayList<>();
        for(int i = 0; i < globalScores.size(); i++){
            if(globalScores.get(i).getUsername().equals(current_player.getAccount())){
                UserScores.add(globalScores.get(i));
            }
        }
        return UserScores;
    }


}
