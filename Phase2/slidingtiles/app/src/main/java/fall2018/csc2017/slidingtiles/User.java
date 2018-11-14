package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A User.
 */

public class User implements Serializable {
    private String account;
    private String password;
    private String gameFile;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";
    }

    /**
     * Returns the file containing this user's data.
     */
    public String getGameFile() {
        return gameFile;
    }

    /**
     * Returns the string identifier for this user.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Returns this user's password.
     */
    public String getPassword() { return password; }
}
