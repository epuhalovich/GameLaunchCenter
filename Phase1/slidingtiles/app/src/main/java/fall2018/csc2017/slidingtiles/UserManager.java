package fall2018.csc2017.slidingtiles;


import android.content.Context;
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

public class UserManager implements Serializable {
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

    public void signUp(String account, String password){
//        if (hasAccount(account) != -1) {
//            throw new MissingFormatArgumentException("This username has been registered.");
//        }
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

    public boolean signIn (String account, String password){
//        if (hasAccount(account) == -1) {
//            throw new NoSuchElementException("Username is not found.");
//        }
        int index = hasAccount(account);
        return (allUsers.get(index).getPassword()).equals(password);
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