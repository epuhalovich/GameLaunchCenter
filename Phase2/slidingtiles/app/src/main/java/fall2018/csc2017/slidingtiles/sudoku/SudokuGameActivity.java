package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.CustomAdapter;

public class SudokuGameActivity extends AppCompatActivity implements Observer, Serializable {




    // the puzzle
    public List<List<String>> puzzle;


    // private MyView myView;
    private static int columnWidth, columnHeight;
    private SudokuGestureDetectGridView gridView;

    // public MyView myView = new MyView(this);



    public void display() {
        SudokuStartingActivity.controller.updateTileButtons();
        gridView.setAdapter(new CustomAdapter(SudokuStartingActivity.controller.getBoxButtons(), columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("1");
            makeToastChooseSpot();
        });
    }

    private void addTwoButtonListener() {
        Button button = findViewById(R.id.number2);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("2");
            makeToastChooseSpot();
        });
    }

    private void addThreeButtonListener() {
        Button button = findViewById(R.id.number3);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("3");
            makeToastChooseSpot();
        });
    }

    private void addFourButtonListener() {
        Button button = findViewById(R.id.number4);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("4");
            makeToastChooseSpot();
        });
    }

    private void addFiveButtonListener() {
        Button button = findViewById(R.id.number5);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("5");
            makeToastChooseSpot();
        });
    }

    private void addSixButtonListener() {
        Button button = findViewById(R.id.number6);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("6");
            makeToastChooseSpot();
        });
    }

    private void addSevenButtonListener() {
        Button button = findViewById(R.id.number7);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("7");
            makeToastChooseSpot();
        });
    }

    private void addEightButtonListener() {
        Button button = findViewById(R.id.number8);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("8");
            makeToastChooseSpot();
        });
    }

    private void addNineButtonListener() {
        Button button = findViewById(R.id.number9);
        button.setOnClickListener(v -> {
            SudokuStartingActivity.controller.getGameManager().setNumberToFill("9");
            makeToastChooseSpot();
        });
    }

    private void makeToastChooseSpot() {
        Toast.makeText(this, "Please choose a spot to fill in this number", Toast.LENGTH_SHORT).show();
    }

    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.sudokuundo);
        undoButton.setOnClickListener(v -> {
            if (SudokuStartingActivity.controller.getGameManager().getUndoPositionStack().empty()){
                makeToastNothingToUndo();
            } else {
                SudokuStartingActivity.controller.getGameManager().tryUndo();
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
    //initView with gridView
        gridView = findViewById(R.id.sudokugrid);
        gridView.setNumColumns(9);
        gridView.setmController(new SudokuMovementController());
        gridView.setGameManager(SudokuStartingActivity.controller.getGameManager());
        SudokuStartingActivity.controller.createTileButtons(this);
        gridView.setButtonArrayList(SudokuStartingActivity.controller.getBoxButtons());
        SudokuStartingActivity.controller.getGameManager().addObserver(this);
        SudokuMovementController movementController = (SudokuMovementController)gridView.getmController();
        movementController.setGameActivity(this);
        movementController.addObserver(this);
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
        SudokuStartingActivity.controller.notifyObservers();
        if(SudokuStartingActivity.controller.checkToAddScore(SudokuStartingActivity.scoreboard, LogInActivity.currentPlayer.getAccount()))
        {switchToScoreBoard();}
    }

    private void switchToScoreBoard(){
        Intent tmp = new Intent(this, SudokuScoreboardActivity.class);
        startActivity(tmp);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}