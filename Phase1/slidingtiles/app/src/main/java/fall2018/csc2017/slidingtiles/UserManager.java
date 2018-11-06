package fall2018.csc2017.slidingtiles;


import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.content.Context;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;

/**
 * A UserManager to manage users.
 */

public class UserManager implements Serializable{

    /**
     * A list that store all users.
     */
    private static ArrayList<User> allUsers = new ArrayList<>();

    /**
     * A name of the file that store the object UserManager.
     */
    private static final String fileName = "allUsers.ser";

    /**
     * A context.
     */
    private Context context;

    /**
     * Construct a new UserManager with context and load it from "allUsers.ser" if needed.
     * @param context the context to store UserManager in the phone's storage
     */

    public UserManager(Context context) {
        this.context = context;
        loadFromFile();
    }

    /**
     * Return the index of the user in UserManger iff there exists a user with the account name.
     * Otherwise, return -1 iff the user doesn't exist.
     *
     * @param account username
     * @return the index
     */
    public int hasAccount(String account){
        if (allUsers.size() != 0){
            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getAccount().equals(account)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Add an account in the UserManager iff user fills in the valid signUp information
     * (account name and password) and write it in the file "allUsers.ser".
     *
     * @param account the username that user provides.
     * @param password the password that user provides.
     * @throws DuplicateException if a same username has already exists
     * @throws AccountsException if user doesn't provide username
     * @throws NoPassWordException if user doesn't provide password
     */
    public void signUp(String account, String password) throws DuplicateException, AccountsException,
            NoPassWordException {
       if (hasAccount(account) != -1) {
           throw new DuplicateException();
       }
       else if (account.length() == 0) {
           throw new AccountsException();
       }
       else if (password.length() == 0){
           throw new NoPassWordException();
      }
           allUsers.add(new User(account, password));
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(allUsers);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Return the user object iff the password that the user provides matches the password
     * of the account which user have.
     * @param account the username
     * @param password the password that user enter
     * @return the User
     * @throws AccountsException if the password is incorrect
     */
    public User signIn (String account, String password) throws AccountsException{
        int index = hasAccount(account);
        if (index == -1) {
            throw new AccountsException();
        }
        else if (!(allUsers.get(index).getPassword()).equals(password)) {
            throw new AuthenticatorException();
        }
        else{
        return allUsers.get(index);
        }
    }

    /**
     *  Load the UserManager.
     */
    private void loadFromFile() {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                allUsers = (ArrayList<User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}
