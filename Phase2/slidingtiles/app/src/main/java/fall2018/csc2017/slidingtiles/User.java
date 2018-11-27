package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * A User.
 */

public class User implements Serializable {
    private String account;
    private String password;
    private String gameFile;
    private String sudokuFile;


    public User(String account, String password) {
        this.account = account;
        this.password = password;
        this.gameFile = this.account + ".ser";
        this.sudokuFile = this.account + "sudoku.ser";
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

    public String getSudokuFile(){return  sudokuFile;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User o = (User) obj;
            return(o.getAccount().equals(this.account));
        }
        return false;
    }
}
