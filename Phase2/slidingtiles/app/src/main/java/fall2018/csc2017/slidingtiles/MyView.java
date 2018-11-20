package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    private float boxSide;
    private int X;
    private int Y;

//    SudoGame sudo = new SudoGame();

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
            // the way to draw the number on the box on the first grid
            // rectangle's x center = text's x center
            // rec' y center - (text' top + test's bottom) / 2 = text's x center
            @SuppressLint("DrawAllocation") Paint numberPaint = new Paint();
            numberPaint.setColor(Color.BLACK);
            //设置空心
            numberPaint.setStyle(Paint.Style.STROKE);
            //设置文字大小为0.75 单元格 大小
            numberPaint.setTextSize(boxSide * 0.75f);
            //设置文字居中对齐
            numberPaint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();

            float x = boxSide / 2;
            //x默认是‘3’这个字符的左边在屏幕的位置，如果设置了
            //paint.setTextAlign(Paint.Align.CENTER);
            //那就是字符的中心，y是指定这个字符baseline在屏幕上的位置
            canvas.drawText("1",20 + x,20 + x - ((fontMetrics.top + fontMetrics.bottom)) / 2, numberPaint);
//
//            super.onDraw(canvas);
        }}}
