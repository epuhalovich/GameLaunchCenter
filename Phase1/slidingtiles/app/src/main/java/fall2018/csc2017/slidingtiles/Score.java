package fall2018.csc2017.slidingtiles;


/**
 * Score object class
 */

public class Score implements Comparable<Score>{
    private User user;
    private int score;

    /**
     * Creates a score object
     * @param user the user the score belongs to
     * @param score numerical representation of score
     */
    public Score(User user, int score){
        this.user = user;
        this.score = score;
    }

    /**
     * Returns the numerical representation of score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the username that the score belongs to
     * @return username
     */
    public String getUsername(){
        return user.getAccount();
    }

    /**
     * Making the Scores comparable
     * @param o Score being compared
     * @return -1, 1, 0 if less than, greater than, or equal too.
     */
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
