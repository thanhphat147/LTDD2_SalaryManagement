package tdc.edu.vn.drawingfun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

public class PacmanDraw1 extends View {

    private Paint pacmanPaint;
    private Paint eyeOfPacmanPaint;
    private  Paint dotPaint;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private Path path = new Path();
    private Paint paint = new Paint();
    private RectF mArcBounds = new RectF();


    public PacmanDraw1(Context context) {
        super(context);
        initPaint();
    }

    public PacmanDraw1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PacmanDraw1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PacmanDraw1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPaint() {
        pacmanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pacmanPaint.setColor(Color.RED);
        eyeOfPacmanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyeOfPacmanPaint.setColor(Color.BLACK);
        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setColor(Color.BLUE);
    }





    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i;
        int square = 300;
        float top = 100;
        float left = 100;
        float right = left + square;
        float bottom = top + square;
        mArcBounds.set(left, top, right, bottom);
        canvas.drawArc(mArcBounds, 30f, 300f, true, pacmanPaint);
        mCenterX = left + 180;
        mCenterY = top + 70;
        mRadius = 25;
        canvas.drawCircle(mCenterX, mCenterY, mRadius, eyeOfPacmanPaint);
        mCenterX = left + 300;
        mCenterY = top + 150;
        mRadius = 35;
        canvas.drawCircle(mCenterX, mCenterY, mRadius, dotPaint);
        for(i = 0; i < 6; i++){
            canvas.drawCircle(mCenterX + 100*i, mCenterY, mRadius, dotPaint);
        }
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

}
