package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesController;

import static android.content.Context.MODE_PRIVATE;

public class GameFileSaver implements Serializable, PhaseTwoObserver {
    private Context context;
    private GameController subject;
    private GameManager gameManager;
    private String fileName;


    public GameFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager manager) {
        this.gameManager = manager;
    }

    public void setSubject(PhaseTwoSubject subject){
        this.subject = (GameController) subject;
    }

    public void update() {
        setGameManager(subject.getGameManager());
        saveToFile();
    }

    public void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(gameManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public void loadFromFile() {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameManager = (GameManager) input.readObject();
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
