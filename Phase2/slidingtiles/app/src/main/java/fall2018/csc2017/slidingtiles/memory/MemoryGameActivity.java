package fall2018.csc2017.slidingtiles.memory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.CustomAdapter;
import fall2018.csc2017.slidingtiles.LogInActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.slidingtiles.GestureDetectGridView;

public class MemoryGameActivity extends AppCompatActivity implements Observer, Serializable {

        // Grid View and calculated column height and width based on device size
        private GestureDetectGridView gridView;
        private static int columnWidth, columnHeight;
        private int NUM_COLS, NUM_ROWS;

        /**
         * Set up the background image for each button based on the master list
         * of positions, and then call the adapter to set the view.
         */
        // Display
        public void display() {
            MemoryStartingActivity.controller.updateTileButtons();
            gridView.setAdapter(new CustomAdapter(MemoryStartingActivity.controller.getTileButtons(), columnWidth, columnHeight));
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.NUM_COLS = MemoryStartingActivity.controller.getGameManager().getMemoryBoard().getNUM_COLS();
            this.NUM_ROWS = MemoryStartingActivity.controller.getGameManager().getMemoryBoard().getNUM_ROWS();
            MemoryStartingActivity.controller.createTileButtons(this);
            setContentView(R.layout.activity_memory);
            addSaveButtonListener();


            // Add View to activity
            gridView = findViewById(R.id.grid);
            gridView.setmController(new MemoryMovementController());
            gridView.setNumColumns(NUM_COLS);
            gridView.setGameManager(MemoryStartingActivity.controller.getGameManager());
            MemoryStartingActivity.controller.getGameManager().getMemoryBoard().addObserver(this);
            // Observer sets up desired dimensions as well as calls our display function
            gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                    this);
                            int displayWidth = gridView.getMeasuredWidth();
                            int displayHeight = gridView.getMeasuredHeight();

                            columnWidth = displayWidth / NUM_COLS;
                            columnHeight = displayHeight / NUM_ROWS;

                            display();
                        }
                    });
        }

        /**
         * Activate the save button.
         */
        private void addSaveButtonListener() {
            Button saveButton = findViewById(R.id.save);
            saveButton.setOnClickListener(v -> {
                MemoryStartingActivity.controller.notifyObservers();
                makeToastSavedText();
            });
        }

        /**
         * Display that a game was saved successfully.
         */
        private void makeToastSavedText() {
            Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
        }

        /**
        * Display that a game was saved successfully.
         */
        public void makeToastMatchText() {
            Toast.makeText(this, "It's a match!", Toast.LENGTH_SHORT).show();
        }

        /**
         * Dispatch onPause() to fragments.
         */
        @Override
        protected void onPause() {
            super.onPause();
            MemoryStartingActivity.controller.notifyObservers();
        }


        @Override
        public void update(Observable o, Object arg) {
            display();
           MemoryStartingActivity.controller.notifyObservers();
            if(MemoryStartingActivity.controller.checkToAddScore(MemoryStartingActivity.scoreboard, LogInActivity.currentPlayer.getAccount()))
            {switchToScoreBoard();}
        }

        private void switchToScoreBoard(){
            Intent tmp = new Intent(this, MemoryScoreboardActivity.class);
            startActivity(tmp);
        }

}






