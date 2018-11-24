package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import fall2018.csc2017.slidingtiles.slidingtiles.GestureDetectGridView;
import fall2018.csc2017.slidingtiles.slidingtiles.MovementController;

class MyView extends View {
//    private View view;
    private List<List<String>> answer;
    private List<List<String>> puzzle;
    private SudokuManager sudokuManager;
//    private SudokuBoard sudokuBoard = new SudokuBoard(40);
    private float boxSide;
    private int phoneWidth;
    private GestureDetector gDetector;
//    private SudokuMovementController mController;
    private boolean mFlingConfirmed = false;
    private float mTouchX;
    private float mTouchY;

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context) {
        super(context);

        initView();
    }

    private void initView(){

    }

//    SudoGame sudo = new SudokuManager();


//    public MyView(Context context) {
//        super(context);
//        init(context);
//    }

    public void setSudokuManager(SudokuManager sudokuManager) {
        this.sudokuManager = sudokuManager;
    }

    public float getBoxSide() {
        return boxSide;
    }

//    private void init(Context context) {
//        mController = new SudokuMovementController();
//        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//
//            @Override
//            public boolean onSingleTapConfirmed(MotionEvent event) {
//                int position = MyView.this.pointToPosition
//                        (Math.round(event.getX()), Math.round(event.getY()));
//
//                mController.processTapMovement(context, position, true);
//                return true;
//            }
//        });
//    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // the width and height of each small box.
        this.boxSide = (w - 40) /9f;
    }
//    public MyView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
    public void setBoxSide(float framsize){
        this.boxSide = (framsize) / 9;
    }

//    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defstyleRes) {
//        super(context, attrs, defStyleAttr, defstyleRes);
//        init(context);
//    }
//
//    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init();
//    }
//
//    void init() {
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setAntiAlias(true);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation") Paint grey = new Paint();
        grey.setColor(Color.DKGRAY);
        grey.setStrokeWidth(2);

        @SuppressLint("DrawAllocation") Paint black = new Paint();
        black.setColor(Color.BLACK);
        black.setStrokeWidth(5);
        //Draw Line
        for(int i = 0 ;i < 10;  i++){
            if(i % 3 != 0) {
                canvas.drawLine(20, i * boxSide + 20, 20 + 9 * boxSide, i * boxSide + 20, grey);
            canvas.drawLine(i * boxSide + 20, 20,i * boxSide + 20,9 * boxSide + 20 ,grey);
            }
            else {
                canvas.drawLine(20, i * boxSide + 20, 20 + 9 * boxSide,i * boxSide + 20, black);
                canvas.drawLine(i * boxSide + 20, 20,i * boxSide + 20,9 * boxSide + 20,black);
            }
            drawNumber(canvas,sudokuManager.getPuzzle());

        }
    }
//            super.onDraw(canvas);



    // Draw a number on Canvas according to the matrix given.
    private void drawNumber(Canvas canvas, List<List<String>> matrix){
        for(int row = 0; row != 9 ; row++){
            for(int col = 0 ; col != 9; col++){
                Paint numberPaint = new Paint();
                numberPaint.setColor(Color.BLACK);
                numberPaint.setStyle(Paint.Style.STROKE);
                numberPaint.setTextSize(boxSide * 0.75f);
                numberPaint.setTextAlign(Paint.Align.CENTER);
                Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();
                float x = 20 + (col + (float)(0.5)) * boxSide;
                float y = 20 + (row + (float)(0.5)) * boxSide - ((fontMetrics.top +
                        fontMetrics.bottom)) / 2;
                canvas.drawText(matrix.get(row).get(col), x, y, numberPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(ev);
        }
        if (ev.getX()<20 || ev.getY()<20 || ev.getX()>(20 + 9 * boxSide)) {
            Log.e("123", "点到边了");
            return super.onTouchEvent(ev);
        }
        mTouchX = ev.getX();
        mTouchY = ev.getY();
        if (sudokuManager.getemptySpot(changeToIndex(mTouchX,boxSide),changeToIndex(mTouchY,boxSide))){
            return true;
        }
        invalidate();
        return true;

    }

    private int changeToIndex(float view, float boxSide){
        int index = -1;
        for(int i = 0; i!= 9; i++){
            if ((20 + i * boxSide < view) && (20 + (i + 1) * boxSide > view)) {
                index = i;
            }
        }
        return index;
    }

}
