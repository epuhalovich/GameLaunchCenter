//package fall2018.csc2017.slidingtiles;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import fall2018.csc2017.slidingtiles.slidingtiles.SlidingTilesManager;
//
//class SudokuMovementController {
//    private SudokuManager sudokuManager = null;
//
//    public SudokuMovementController() {
//    }
//
//    public void setSudokuManager(SudokuManager sudokuManger) {
//        this.sudokuManager = sudokuManager;
//    }
//
//    public void processTapMovement(Context context, int position, boolean display) {
//        if (sudokuManager.isValidTap(position)) {
//            sudokuManager.touchMove(position);
//            if (sudokuManager.isGameOver()) {
//                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
