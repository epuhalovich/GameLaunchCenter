package fall2018.csc2017.slidingtiles;
import java.util.Map;
import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.Score;

public abstract class Scoreboard {
private static ArrayList<Score> globalScores = new ArrayList<Score>();

public abstract void addScore(User current_player, int score);
public abstract ArrayList<Score> sortScores(ArrayList<Score> scores);

public ArrayList<Score> getGlobalScoreboard(){
    return globalScores;
}
public ArrayList<Score> getUserScoreboard(User current_player){
    ArrayList<Score> UserScores = new ArrayList<Score>();
    for(int i = 0; i < globalScores.size(); i++){
        if(globalScores.get(i).getUsername().equals(current_player.getAccount())){
            UserScores.add(globalScores.get(i));
        }
    }
    return UserScores;
}

}
