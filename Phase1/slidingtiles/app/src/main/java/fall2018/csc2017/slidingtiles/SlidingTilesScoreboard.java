package fall2018.csc2017.slidingtiles;

import java.util.ArrayList;
import java.util.Map;

public class SlidingTilesScoreboard {

    private static ArrayList<Score> globalScores = new ArrayList<Score>();


    public static void addScore(User current_player, int score){
        Score s = new Score(current_player, score);
        globalScores.add(s);
        sortScores(globalScores);
    }
    private static void merge(ArrayList<Score> left, ArrayList<Score> right, ArrayList<Score> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<Score> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    public static ArrayList<Score> sortScores(ArrayList<Score> scores){
        ArrayList<Score> left = new ArrayList<Score>();
        ArrayList<Score> right = new ArrayList<Score>();
        int center;

        if (scores.size() == 1) {
            return scores;
        } else {
            center = scores.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(scores.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<scores.size(); i++) {
                right.add(scores.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = sortScores(left);
            right = sortScores(right);

            // Merge the results back together.
            merge(left, right, scores);
        }
        return scores;
    }
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


