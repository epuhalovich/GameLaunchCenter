package fall2018.csc2017.slidingtiles.sudoku;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

import fall2018.csc2017.slidingtiles.SudokuGameActivity;
import fall2018.csc2017.slidingtiles.SudokuManager;
import fall2018.csc2017.slidingtiles.sudoku.MovementControl;

public class GestureDetectView extends GridView {
    public static final int SWIPE_MIN_DISTANCE = 100;
    public static final int SWIPE_MAX_OFF_PATH = 100;
    public static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetector gDetector;
    private MovementControl mController;
    private boolean mFlingConfirmed = false;
    private float mTouchX;
    private float mTouchY;
    private int boxSide;
    private SudokuManager sudokuManager;

    public GestureDetectView(Context context) {
        super(context);
        init(context);
    }

    public GestureDetectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public GestureDetectView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context) {
        mController = new MovementControl();
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processTapMovement(context, position, true);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }

            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    public void setSudokuManager(SudokuManager sudokuManager) {
        this.sudokuManager = sudokuManager;
        mController.setSudokuManger(sudokuManager);
    }

    public void setBoxSide(int boxSide) {
        this.boxSide = boxSide;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        @SuppressLint("DrawAllocation") Paint grey = new Paint();
//        grey.setColor(Color.DKGRAY);
//        grey.setStrokeWidth(2);
//
//        @SuppressLint("DrawAllocation") Paint black = new Paint();
//        black.setColor(Color.BLACK);
//        black.setStrokeWidth(5);
//        //Draw Line
//        for(int i = 0 ;i < 10;  i++){
//            if(i % 3 != 0) {
//                canvas.drawLine(20, i * boxSide + 20, 20 + 9 * boxSide, i * boxSide + 20, grey);
//                canvas.drawLine(i * boxSide + 20, 20,i * boxSide + 20,9 * boxSide + 20 ,grey);
//            }
//            else {
//                canvas.drawLine(20, i * boxSide + 20, 20 + 9 * boxSide,i * boxSide + 20, black);
//                canvas.drawLine(i * boxSide + 20, 20,i * boxSide + 20,9 * boxSide + 20,black);
//            }
//            drawNumber(canvas,sudokuManager.getPuzzle());
//            if (drawRect){
//                draw(canvas,changeToIndex(mTouchX,boxSide), changeToIndex(mTouchY,boxSide));
//                drawRect = false;

}