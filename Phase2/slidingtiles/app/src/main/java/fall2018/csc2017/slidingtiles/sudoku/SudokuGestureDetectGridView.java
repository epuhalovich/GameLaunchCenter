package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.Button;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.slidingtiles.GestureDetectGridView;


public class SudokuGestureDetectGridView extends GestureDetectGridView {
//    public static final int SWIPE_MIN_DISTANCE = 100;
//    public static final int SWIPE_MAX_OFF_PATH = 100;
//    public static final int SWIPE_THRESHOLD_VELOCITY = 100;
//    private GestureDetector gDetector;
//    private SudokuMovementController mController;
////    private boolean mFlingConfirmed = false;
////    private float mTouchX;
////    private float mTouchY;
////    private ArrayList<Button> buttonArrayList;
////    private int boxSide;
//    private SudokuManager sudokuManager;


    public SudokuGestureDetectGridView(Context context) {
        super(context);
    }

    public SudokuGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SudokuGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas){
        super.dispatchDraw(canvas);
        Paint boarder;
        boarder = new Paint();
        boarder.setStyle(Paint.Style.STROKE);
        boarder.setColor(Color.BLACK);
        boarder.setStrokeWidth(5);
        float height  = getMeasuredHeight() / 9;
        float width  = getMeasuredWidth() / 9;
        for(int i = 0; i <= 9 ;i+=3){
            //draw horizontal line
            canvas.drawLine(0,height * i,getMeasuredWidth() - 5,height * i ,boarder);
            //draw vertical line
            canvas.drawLine(width * i, 0, width * i, getMeasuredHeight() - 5, boarder);
        }

    }
}