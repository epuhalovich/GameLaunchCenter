package fall2018.csc2017.slidingtiles;


import java.io.File;
import java.util.ArrayList;

/**
 * A User.
 */

public class User {
    private String account;
    private String password;
    //    private ArrayList<String> score;
    private String gameFile;
    private int heighest_score;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";
        this.heighest_score = 9999999;
//        this.score = new ArrayList();
        //this.login =?
    }

    public String getGameFile() {
        return gameFile;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() { return password; }

    public int getHeighest_score() { return heighest_score; }

    public void setHeighest_score(int heighest_score) {
        this.heighest_score = heighest_score;
    }
}




