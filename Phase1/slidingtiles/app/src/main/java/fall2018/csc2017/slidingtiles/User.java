package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A User.
 */

public class User implements Serializable {
    /**
     *  A username for the user.
     */
    private String account;

    /**
     * A password for the user.
     */
    private String password;

    /**
     * A game file for the user to store the game.
     */

    private String gameFile;

    /**
     * A highest score for the user in the game.
     */
    private int heighest_score;

    /**
     * A constructor for the user.
     * @param account  the username that the user enter.
     * @param password the password that the user enter.
     */

    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";
        this.heighest_score = 9999999;
    }

    /**
     * A getter of the game file of the user.
     *
     * @return the game file
     */

    public String getGameFile() {
        return gameFile;
    }

    /**
     * A getter of the account of the user.
     * @return the username
     */

    public String getAccount() {
        return account;
    }

    /**
     * A getter of the password of the user.
     * @return the password
     */

    public String getPassword() { return password; }

    /**
     * A getter of the score for the user.
     * @return the score
     */

    public int getHeighest_score() { return heighest_score; }

    /**
     * A setter of the score for the user.
     * @param heighest_score set the score
     */
    public void setHeighest_score(int heighest_score) {
        this.heighest_score = heighest_score;
    }
}




