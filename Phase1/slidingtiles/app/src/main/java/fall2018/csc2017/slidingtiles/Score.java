package fall2018.csc2017.slidingtiles;


import android.support.annotation.NonNull;

public class Score implements Comparable<Score>{
    private User user;
    private int score;
    public Score(User user, int score){
        this.user = user;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public String getUsername(){
        return user.getAccount();
    }

    @Override
    public int compareTo(Score o) {
        if(this.score > o.getScore()) {
            return -1;
        }
        else if(this.score < o.getScore()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
