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

    /**
     * Return user's game file.
     * @return a game file name
     */
    public String getGameFile() {
        return gameFile;
    }

    /**
     * Return user's account.
     * @return the account of user
     */
    public String getAccount() {
        return account;
    }

    /**
     * Return user's password.
     * @return the password of user
     */
    public String getPassword() { return password; }

}




