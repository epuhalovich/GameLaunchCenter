package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

class MyView extends View {
    public MyView(Context context) {
        super(context);
    }
    private SudokuBoard Sudoku = new SudokuBoard();
    private float boxSide;
    private int X = 20;
    private int Y = 20;

//    SudoGame sudo = new SudokuManager();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // the width and height of each small box.
        this.boxSide= (w - 40) /9f;
    }


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
            drawNumber(canvas,Sudoku.listSudoku);

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
    public boolean onTouchEvent(MotionEvent event) {

        if( event.getAction() != event.ACTION_DOWN ) {
            return super.onTouchEvent(event);
        }
        return true;
    }


}
