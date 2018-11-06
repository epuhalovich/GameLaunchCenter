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

    public String getGameFile() {
        return gameFile;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() { return password; }
}
