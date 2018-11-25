package fall2018.csc2017.slidingtiles;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesStartingActivity;

public class SudokuGameActivity extends AppCompatActivity implements Observer, Serializable {


    //SudokuManager
    public SudokuManager sudokuManager;

    // the puzzle
    public List<List<String>> puzzle;

    // the answer
    public List<List<String>> solution;

    private MyView myView;

//    public MyView myView = new MyView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.activity_sudoku);
        initView();
        addUndoButtonListener();
    }

    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.sudokuundo);
        undoButton.setOnClickListener(v -> {
            List<List<String>> before = sudokuManager.getPuzzle();
            sudokuManager.tryUndo();
            if (before == sudokuManager.getPuzzle()){
                makeToastNothingToUndo();
            }
        });
    }

    private void makeToastNothingToUndo() {
        Toast.makeText(this, "there is nothing to undo", Toast.LENGTH_SHORT).show();
    }


    public void initView() {
        MyView myView = new MyView(this);
        myView.setSudokuManager(sudokuManager);
        FrameLayout frameLayout = findViewById(R.id.sudokufram);
//        int side = this.getWindow().getDecorView().getWidth();
////        ConstraintLayout.LayoutParams oldParams= new ConstraintLayout.LayoutParams(side,side);
////        frameLayout.setLayoutParams(oldParams);
        frameLayout.addView(myView);
    }
    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * Load the SudokuManager from fileName.
     *
     */
    private void loadFromFile() {

        try {
            InputStream inputStream = this.openFileInput(SudokuStartingActivity.TEMP_SAVE_FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                sudokuManager = (SudokuManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the sudokuManager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(sudokuManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
