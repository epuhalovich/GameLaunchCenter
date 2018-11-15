package fall2018.csc2017.slidingtiles;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public abstract class Scoreboard implements Serializable {

    public ArrayList<Score> globalScores = new ArrayList<>();


    public Scoreboard(){
   };

    public abstract void addScore(String currentPlayerId, int score);


    public abstract ArrayList<Score> sortScores(ArrayList<Score> scores);

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
