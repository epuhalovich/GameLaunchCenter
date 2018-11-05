package fall2018.csc2017.slidingtiles;


import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.app.AuthenticationRequiredException;
import android.content.Context;
import android.content.res.Resources;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.MissingFormatArgumentException;
import java.util.NoSuchElementException;

import static android.content.Context.MODE_PRIVATE;

/**
 * A UserManager to manage users.
 */

public class UserManager implements Serializable{
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static final String fileName = "allUsers.ser";
    private Context context;


    public UserManager(Context context) {
        this.context = context;
        loadFromFile();
    }

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
//            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public User signIn (String account, String password) throws AccountsException {
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

    private void loadFromFile() {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                allUsers = (ArrayList<User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }
}
