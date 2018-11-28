package fall2018.csc2017.slidingtiles;

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

public class ScoreboardFileSaver implements Serializable, PhaseTwoObserver {
    /**
     * A name of the file that store the object Scoreboard.
     */
    private String fileName;
    /**
     * A context.
     */
    private Context context;

    public ArrayList<Score> globalScores = new ArrayList<>();

    private Scoreboard subject;

    /**
     * Construct a new SlidingTilesScoreboardFileSaver with context and load it from "globalscores.ser" if needed
     * @param context the context to store Scoreboard in the phone's storage
     */
    public ScoreboardFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSubject(PhaseTwoSubject subject){
        this.subject = (Scoreboard) subject;
    }

    public void update() {
        globalScores = subject.getGlobalScores();
        saveToFile(fileName);
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                globalScores = (ArrayList<Score>)input.readObject();
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

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(globalScores);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}