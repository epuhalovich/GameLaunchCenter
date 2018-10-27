package fall2018.csc2017.slidingtiles;


import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.NoSuchElementException;

/**
 * A UserManager to manage users.
 */

public class UserManager {
    private ArrayList<User> allUsers;


    public UserManager() {
        this.allUsers = new ArrayList<>();
    }

    private int hasAccount(String account){
        for (int i = 0; i <= allUsers.size(); i++) {
            if (allUsers.get(i).getAccount().equals(account)) {
                return i;
            }
        }
        return -1;
    }

    public void signUp(String account, String password){
        if (hasAccount(account) != -1) {
            throw new MissingFormatArgumentException("This username has been registered.");
        }
        this.allUsers.add(new User(account, password));
    }

    public boolean signIn (String account, String password){
        if (hasAccount(account) == -1) {
            throw new NoSuchElementException("Username is not found.");
        }
        return allUsers.get(hasAccount(account)).getPassword().equals(password);
    }
}
