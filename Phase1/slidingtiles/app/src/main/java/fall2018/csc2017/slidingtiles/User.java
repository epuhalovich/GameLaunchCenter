package fall2018.csc2017.slidingtiles;


import java.io.File;
import java.util.ArrayList;

/**
 * A User.
 */

public class User {
    private String account;
    private String password;
    private ArrayList<String> score;
    private String gameFile;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";
        this.score = new ArrayList<>();
    }

    public String getGameFile() {
        return gameFile;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {

        return password;
    }

    public ArrayList<String> getScore() {
        return score;
    }

    }





