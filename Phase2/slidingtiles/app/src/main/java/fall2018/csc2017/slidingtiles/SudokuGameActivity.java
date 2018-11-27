package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.sudoku.CustomAdapt;
import fall2018.csc2017.slidingtiles.sudoku.GestureDetectView;
import fall2018.csc2017.slidingtiles.sudoku.SudokuGrid;

public class SudokuGameActivity extends AppCompatActivity implements Observer, Serializable {


    //SudokuManager
    public SudokuManager sudokuManager;

    private ArrayList<Button> BoxButtons;

    // the puzzle
    public List<List<String>> puzzle;

    // the answer
    public List<List<String>> solution;

    // private MyView myView;
    private static int columnWidth, columnHeight;
    private GestureDetectView gridView;

    // public MyView myView = new MyView(this);

    public ArrayList<Button> getBoxButtons(){
        return BoxButtons;
    }


    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapt(BoxButtons, columnWidth, columnHeight));
    }

    public void createTileButtons(Context context) {
        SudokuGrid[][] sudokuPuzzle = sudokuManager.getPuzzle();
        BoxButtons = new ArrayList<>();
        for (int row = 0; row != 9; row++) {
            for (int col = 0; col != 9; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(sudokuPuzzle[row][col].getBackground());
                this.BoxButtons.add(tmp);
            }
        }
    }

    private void updateTileButtons() {
        SudokuGrid[][] sudokuBoard = sudokuManager.getPuzzle();
        int buttonPosition = 0;
        for(int row = 0; row != 9; row ++){
            for(int col = 0; col != 9; col++){
                Button b = BoxButtons.get(buttonPosition);
                b.setText(sudokuBoard[row][col].getNumber());
                b.setTextSize(17);
                b.setBackgroundResource(sudokuBoard[row][col].getBackground());
                buttonPosition ++;
                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.activity_sudoku);
        initView();
        addUndoButtonListener();
        addOneButtonListener();
        addTwoButtonListener();
        addThreeButtonListener();
        addFourButtonListener();
        addFiveButtonListener();
        addSixButtonListener();
        addSevenButtonListener();
        addEightButtonListener();
        addNineButtonListener();
    }

    private void addOneButtonListener() {
        Button button = findViewById(R.id.number1);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("1");
            makeToastChooseSpot();
        });
    }

    private void addTwoButtonListener() {
        Button button = findViewById(R.id.number2);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("2");
            makeToastChooseSpot();
        });
    }

    private void addThreeButtonListener() {
        Button button = findViewById(R.id.number3);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("3");
            makeToastChooseSpot();
        });
    }

    private void addFourButtonListener() {
        Button button = findViewById(R.id.number4);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("4");
            makeToastChooseSpot();
        });
    }

    private void addFiveButtonListener() {
        Button button = findViewById(R.id.number5);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("5");
            makeToastChooseSpot();
        });
    }

    private void addSixButtonListener() {
        Button button = findViewById(R.id.number6);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("6");
            makeToastChooseSpot();
        });
    }

    private void addSevenButtonListener() {
        Button button = findViewById(R.id.number7);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("7");
            makeToastChooseSpot();
        });
    }

    private void addEightButtonListener() {
        Button button = findViewById(R.id.number8);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("8");
            makeToastChooseSpot();
        });
    }

    private void addNineButtonListener() {
        Button button = findViewById(R.id.number9);
        button.setOnClickListener(v -> {
            sudokuManager.setNumberToFill("9");
            makeToastChooseSpot();
        });
    }

    private void makeToastChooseSpot() {
        Toast.makeText(this, "Please choose a spot to fill in this number", Toast.LENGTH_SHORT).show();
    }

    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.sudokuundo);
        undoButton.setOnClickListener(v -> {
            if (sudokuManager.getUndoPositionStack().empty()){
                makeToastNothingToUndo();
            } else {
                sudokuManager.tryUndo();
                makeToastUndoSuccessful();
            }
        });
    }

    private void makeToastNothingToUndo() {
        Toast.makeText(this, "there is nothing to undo", Toast.LENGTH_SHORT).show();
    }

    private void makeToastUndoSuccessful() {
        Toast.makeText(this, "undo successfully", Toast.LENGTH_SHORT).show();
    }

    public void initView() {
//        init View with My View
//        MyView myView = new MyView(this);
//        myView.setSudokuManager(sudokuManager);
//        FrameLayout frameLayout = findViewById(R.id.sudokufram);
//        int side = this.getWindow().getDecorView().getWidth();
////        ConstraintLayout.LayoutParams oldParams= new ConstraintLayout.LayoutParams(side,side);
////        frameLayout.setLayoutParams(oldParams);
//        frameLayout.addView(myView);

    //initView with gridView
        gridView = findViewById(R.id.sudokugrid);
        gridView.setNumColumns(9);
        gridView.setSudokuManager(sudokuManager);
        createTileButtons(this);
        gridView.setButtonArrayList(BoxButtons);
        sudokuManager.addObserver(this);
        gridView.getmController().setGameActivity(this);
        gridView.getmController().addObserver(this);
//        SlidingTilesStartingActivity.controller.getSlidingTilesManager().getSlidingTilesBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / 9;
                        columnHeight = displayHeight / 9;
//                        gridView.setBoxSide(displayWidth / 9);

                        display();
                    }
                });
    }
    @Override
    public void update(Observable observable, Object o) {
        display();
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
