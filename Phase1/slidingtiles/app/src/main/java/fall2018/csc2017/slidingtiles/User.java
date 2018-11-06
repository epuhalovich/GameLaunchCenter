package fall2018.csc2017.slidingtiles;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A User.
 */

public class User implements Serializable {
    private String account;
    private String password;
    private String gameFile;
    private int heighest_score;

    /**
     * Constructs a user
     * @param account the username
     * @param password the password
     */
    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";

    }

    public String getGameFile() {
        return gameFile;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() { return password; }

}




