package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;


public class SudokuFileSaver implements Serializable, PhaseTwoObserver {

    private Context context;
    private SudokuController subject;
    private SudokuManager sudokuManager;
    private String fileName;

    public SudokuFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();
    }
    public void setSubject(PhaseTwoSubject subject){
        this.subject = (SudokuController) subject;
    }

    public void setSudokuManager(SudokuManager sudokuManager) {
        this.sudokuManager = sudokuManager;
    }

    public SudokuManager getSudokuManager(){
        return this.sudokuManager;
    }

    public void update() {
        setSudokuManager(subject.getSudokuManager());
        saveToFile();
    }

    public void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(sudokuManager);
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
                sudokuManager = (SudokuManager) input.readObject();
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
