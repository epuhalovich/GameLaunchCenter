package fall2018.csc2017.slidingtiles.slidingtiles;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fall2018.csc2017.slidingtiles.PhaseTwoObserver;
import fall2018.csc2017.slidingtiles.PhaseTwoSubject;

import static android.content.Context.MODE_PRIVATE;


public class SlidingTilesFileSaver implements Serializable, PhaseTwoObserver {
    private Context context;
    private SlidingTilesController subject;
    private SlidingTilesManager slidingTilesManager;
    private String fileName;


    public SlidingTilesFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();

    }

    public SlidingTilesManager getSlidingTilesManager() {
        return slidingTilesManager;
    }

    public void setSlidingTilesManager(SlidingTilesManager slidingTilesManager) {
        this.slidingTilesManager = slidingTilesManager;
    }

    public void setSubject(PhaseTwoSubject subject){
        this.subject = (SlidingTilesController) subject;
    }

    public void update() {
        setSlidingTilesManager(subject.getSlidingTilesManager());
        saveToFile();
    }

    public void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(slidingTilesManager);
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
                slidingTilesManager = (SlidingTilesManager) input.readObject();
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
