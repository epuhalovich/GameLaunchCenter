package fall2018.csc2017.slidingtiles.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.slidingtiles.GestureDetectGridView;


public class SudokuGestureDetectGridView extends GestureDetectGridView {

    /**
     * Initialize the SudokuGestureDetectGridView() with context.
     * @param context the context.
     */
    public SudokuGestureDetectGridView(Context context) {
        super(context);
    }

    public SudokuGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SudokuGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * Draw the row and column line in gridView.
     * @param canvas the canvas
     */
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