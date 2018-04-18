package com.yugansh.tyagi.smileyrating;

import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
public class SmileyRating extends View {

    private int faceColor, eyesColor, mouthColor, tongueColor;
    private RectF neutralOval,slightOval,happyOval,amazingOval, tongueOval, currRect;
    private Paint paint;
    int centerOffset, viewWidth, viewHeight, whatToDraw = 0;

    public SmileyRating(Context context) {
        super(context);
    }

    public SmileyRating(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Initializing objects
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        neutralOval = new RectF();
        slightOval = new RectF();
        happyOval = new RectF();
        amazingOval = new RectF();
        tongueOval = new RectF();
        currRect = new RectF();

        //Getting attributes value
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmileyRating);
        try {
            faceColor = typedArray.getColor(R.styleable.SmileyRating_faceColor,
                    getResources().getColor(R.color.faceColor));
            eyesColor = typedArray.getColor(R.styleable.SmileyRating_eyesColor,
                    getResources().getColor(R.color.eyesColor));
            mouthColor = typedArray.getColor(R.styleable.SmileyRating_mouthColor,
                    getResources().getColor(R.color.mouthColor));
            tongueColor = typedArray.getColor(R.styleable.SmileyRating_tongueColor,
                    getResources().getColor(R.color.tongueColor));
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        centerOffset = viewHeight / 3;
        Log.d("Height", String.valueOf(viewHeight / 3));
        neutralOval.set(-centerOffset, -viewHeight, viewWidth + centerOffset, viewHeight);

        slightOval.set((viewWidth / 2) - 380,viewHeight-700,
                        (viewWidth / 2) + 380,viewHeight-200);

        happyOval.set((viewWidth / 2) - 400,viewHeight-1000,
                (viewWidth / 2) + 400,viewHeight-150);

        amazingOval.set((viewWidth / 2) - 430,viewHeight-1000,
                (viewWidth / 2) + 430,viewHeight-150);

        tongueOval.set((viewWidth / 2) - 230,viewHeight-650,
                (viewWidth / 2) + 230,viewHeight-200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (whatToDraw) {
            case 0:
                drawNeutralFace(canvas);
                break;
            case 1:
               drawNeutralFace(canvas);
                break;
            case 2:
                drawSlightSmileFace(canvas);
                break;
            case 3:
                drawHappyFace(canvas);
                break;
            case 4:
                drawAmazingFace(canvas);
                break;
        }
    }

    private void drawNeutralFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval, 0, 180, true, paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth / 2) - 280, 200, 80, paint);
        canvas.drawCircle((viewWidth / 2) + 280, 200, 80, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(60);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine((viewWidth / 2) - 360,
                viewHeight - 360,
                (viewWidth / 2) + 360,
                viewHeight - 360, paint);

        currRect = neutralOval;

        invalidate();
        requestLayout();
    }

    private void drawSlightSmileFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval,0,180,true,paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth/2)-160, 210, 80, paint);
        canvas.drawCircle((viewWidth/2)+160, 210, 80, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(60);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeMiter(30f);
        canvas.drawArc(slightOval, 0,180,false,paint);
        invalidate();
        requestLayout();
    }

    private void drawHappyFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval,0,180,true,paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth/2)-200, 220, 80, paint);
        canvas.drawCircle((viewWidth/2)+200, 220, 80, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(60);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeMiter(30f);
        canvas.drawArc(happyOval, 0,180,false,paint);
        invalidate();
        requestLayout();
    }

    private void drawAmazingFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval,0,180,true,paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth/2)-230, 240, 80, paint);
        canvas.drawCircle((viewWidth/2)+230, 240, 80, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(60);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeMiter(30f);
        canvas.drawArc(happyOval, 0,180,true,paint);

        //Draw tongue
        paint.setColor(tongueColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(tongueOval, 0, 180, true, paint);

        invalidate();
        requestLayout();
    }

    private RectF getCurrRect(){
        return currRect;
    }



    public void setSmiley(float rating) {
        switch ((int) rating) {
            case 0:
                whatToDraw = 0;
                invalidate();
                break;
            case 1:
                whatToDraw = 1;
                invalidate();
                break;
            case 2:
                whatToDraw = 2;
                invalidate();
                break;
            case 3:
                whatToDraw = 3;
                invalidate();
                break;
            case 4:
                whatToDraw = 4;
                invalidate();
                break;
        }
    }

    class PositionAnimator extends RectEvaluator{

        private RectF oldPosition,newPosition;

        @Override
        public Rect evaluate(float fraction, Rect startValue, Rect endValue) {
            return super.evaluate(fraction, startValue, endValue);
        }
    }
}
