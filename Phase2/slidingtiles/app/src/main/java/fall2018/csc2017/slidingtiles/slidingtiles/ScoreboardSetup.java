package fall2018.csc2017.slidingtiles.slidingtiles;
import java.util.ArrayList;
import java.util.Locale;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.Score;
import fall2018.csc2017.slidingtiles.User;

public class ScoreboardSetup {

    /**
     * The scoreboard containing all the user and score data for sliding tiles.
     */
    private SlidingTilesScoreboard slidingTilesScoreboard;

    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer;

    public ScoreboardSetup(SlidingTilesScoreboard slidingTilesScoreboard,User currentPlayer ){
        this.slidingTilesScoreboard = slidingTilesScoreboard;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks the Sliding Tiles scoreboard for the top scores.
     * If userScoresOnly is true, only looks up scores for the current player.
     * Returns a string of the top 5 (or less if less than 5 exist) scores for this game.
     *
     * @param userScoresOnly true when looking only for current player's scores
     * @return scoreValues
     */
    public String getScoreValues(boolean userScoresOnly) {

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

}
