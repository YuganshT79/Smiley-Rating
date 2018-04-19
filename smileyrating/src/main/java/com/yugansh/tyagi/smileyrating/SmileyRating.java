package com.yugansh.tyagi.smileyrating;

import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
public class SmileyRating extends View {

    private int faceColor, eyesColor, mouthColor, tongueColor;
    private RectF neutralOval, slightOval, happyOval, amazingOval, tongueOval, currRect;
    private Paint paint;
    int centerOffset, viewWidth, viewHeight, whatToDraw = 0, strokeWidth, eyeRadius;

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

        strokeWidth = viewHeight/30 + viewWidth/30;
        eyeRadius = viewHeight/25 + viewWidth/25;
        centerOffset = viewHeight / 3;

        neutralOval.set(-centerOffset, -viewHeight, viewWidth + centerOffset, viewHeight);

        slightOval.set((viewWidth / 2) - (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 60),
                (viewWidth / 2) + (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 20));

        happyOval.set((viewWidth / 2) - (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 90),
                (viewWidth / 2) + (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 20));

        amazingOval.set((viewWidth / 2) - (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 90),
                (viewWidth / 2) + (viewWidth/100 * 35), viewHeight - (viewHeight/100 * 15));

        tongueOval.set((viewWidth / 2) - (viewWidth/100 * 20), viewHeight - (viewHeight/100 * 60),
                (viewWidth / 2) + (viewWidth/100 * 20), viewHeight - (viewHeight/100 * 20));
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
        canvas.drawCircle((viewWidth / 2) - (viewWidth/100 * 25),
                (viewHeight/100 * 20), eyeRadius, paint);
        canvas.drawCircle((viewWidth / 2) + (viewWidth/100 * 25),
                (viewHeight/100 * 20), eyeRadius, paint);
        Log.d("Eyes Radius", String.valueOf(eyeRadius));

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine((viewWidth / 2) - (viewWidth/100 * 30),
                viewHeight - (viewHeight/100 * 30),
                (viewWidth / 2) + (viewWidth/100 * 30),
                viewHeight - (viewHeight/100 * 30), paint);

        currRect = neutralOval;

        invalidate();
        requestLayout();
    }

    private void drawSlightSmileFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval, 0, 180, true, paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth / 2) - (viewWidth/100 *18),
                (viewHeight/100 *23), eyeRadius, paint);
        canvas.drawCircle((viewWidth / 2) + (viewWidth/100 *18),
                (viewHeight/100 *23), eyeRadius, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(slightOval, 0, 180, false, paint);
        invalidate();
        requestLayout();
    }

    private void drawHappyFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval, 0, 180, true, paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth / 2) - (viewWidth/100 * 18),
                (viewHeight/100 * 22), eyeRadius, paint);
        canvas.drawCircle((viewWidth / 2) + (viewWidth/100 * 18),
                (viewHeight/100 * 22), eyeRadius, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(60);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeMiter(30f);
        canvas.drawArc(happyOval, 0, 180, false, paint);
        invalidate();
        requestLayout();
    }

    private void drawAmazingFace(Canvas canvas) {
        //Draw face BG
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(neutralOval, 0, 180, true, paint);

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((viewWidth / 2) - (viewWidth/100 * 23),
                (viewHeight/100 * 23), eyeRadius, paint);
        canvas.drawCircle((viewWidth / 2) + (viewWidth/100 * 23),
                (viewHeight/100 * 23), eyeRadius, paint);


        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(amazingOval, 0, 180, true, paint);

        //Draw tongue
        paint.setColor(tongueColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(tongueOval, 0, 180, true, paint);

        invalidate();
        requestLayout();
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

    public class CRectFEvaluator implements TypeEvaluator<RectF> {


        private RectF mRectF;
        public CRectFEvaluator() {
        }


        public CRectFEvaluator(RectF reuseRect) {
            mRectF = reuseRect;
        }

        @Override
        public RectF evaluate(float fraction, RectF startValue, RectF endValue) {
            float left = startValue.left + (endValue.left - startValue.left) * fraction;
            float top = startValue.top + (endValue.top - startValue.top) * fraction;
            float right = startValue.right + (endValue.right - startValue.right) * fraction;
            float bottom = startValue.bottom + (endValue.bottom - startValue.bottom) * fraction;
            if (mRectF == null) {
                return new RectF(left, top, right, bottom);
            } else {
                mRectF.set(left, top, right, bottom);
                return mRectF;
            }
        }
    }

}
