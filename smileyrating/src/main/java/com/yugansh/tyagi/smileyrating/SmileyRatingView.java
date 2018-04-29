package com.yugansh.tyagi.smileyrating;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
public class SmileyRatingView extends View {

    private int faceColor, eyesColor, mouthColor, tongueColor;
    private RectF faceBgOval, sadOval, neutralOval, slightHappyOval, happyOval, amazingOval, tongueOval;
    private Paint paint;
    int centerOffset, viewWidth, viewHeight,
            whatToDraw = 2, defaultRating, strokeWidth, eyeRadius;

    int currEyeLX, currEyeRX, currEyeY;

    ValueAnimator rightEyeAnimatorX, leftEyeAnimatorX, eyesAnimatorY;
    final long animationDuration = 300;

    public SmileyRatingView(Context context) {
        super(context);

        //Disable Hardware acceleration on device with API < 18
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public SmileyRatingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Initializing objects
        paint = new Paint();
        faceBgOval = new RectF();
        sadOval = new RectF();
        neutralOval = new RectF();
        slightHappyOval = new RectF();
        happyOval = new RectF();
        amazingOval = new RectF();
        tongueOval = new RectF();
        leftEyeAnimatorX = new ValueAnimator();
        rightEyeAnimatorX = new ValueAnimator();
        eyesAnimatorY = new ValueAnimator();

        //Getting attributes value
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmileyRatingView);
        try {
            faceColor = typedArray.getColor(R.styleable.SmileyRatingView_face_color,
                    getResources().getColor(R.color.faceColor));
            eyesColor = typedArray.getColor(R.styleable.SmileyRatingView_eyes_color,
                    getResources().getColor(R.color.eyesColor));
            mouthColor = typedArray.getColor(R.styleable.SmileyRatingView_mouth_color,
                    getResources().getColor(R.color.mouthColor));
            tongueColor = typedArray.getColor(R.styleable.SmileyRatingView_tongue_color,
                    getResources().getColor(R.color.tongueColor));
            defaultRating = typedArray.getInteger(
                    R.styleable.SmileyRatingView_default_rating, 2);
        } finally {
            typedArray.recycle();
        }
        whatToDraw = defaultRating;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();

        strokeWidth = viewHeight / 30 + viewWidth / 30;
        eyeRadius = viewHeight / 25 + viewWidth / 25;
        centerOffset = viewHeight / 3;

        switch (whatToDraw) {
            case 0:
                currEyeLX = (viewWidth / 2) - (viewWidth / 100 * 25);
                currEyeRX = (viewWidth / 2) + (viewWidth / 100 * 25);
                currEyeY = (viewHeight / 100 * 20);
                break;
            case 1:
                currEyeLX = (viewWidth / 2) - (viewWidth / 100 * 20);
                currEyeRX = (viewWidth / 2) + (viewWidth / 100 * 20);
                currEyeY = (viewHeight / 100 * 20);
                break;
            case 2:
                currEyeLX = (viewWidth / 2) - (viewWidth / 100 * 17);
                currEyeRX = (viewWidth / 2) + (viewWidth / 100 * 17);
                currEyeY = (viewHeight / 100 * 25);
                break;
            case 3:
                currEyeLX = (viewWidth / 2) - (viewWidth / 100 * 19);
                currEyeRX = (viewWidth / 2) + (viewWidth / 100 * 19);
                currEyeY = (viewHeight / 100 * 22);
                break;
            case 4:
                currEyeLX = (viewWidth / 2) - (viewWidth / 100 * 23);
                currEyeRX = (viewWidth / 2) + (viewWidth / 100 * 23);
                currEyeY = (viewHeight / 100 * 23);
                break;
        }

        faceBgOval.set(-centerOffset, -viewHeight, viewWidth + centerOffset, viewHeight);

        sadOval.set((viewWidth / 2) - (viewWidth / 100 * 25), viewHeight - (viewHeight / 100 * 45),
                (viewWidth / 2) + (viewWidth / 100 * 25), viewHeight - (viewHeight / 100 * 10));

//        neutralOval.set((viewWidth / 2) - (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40,
//                (viewWidth / 2) + (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40);

        slightHappyOval.set((viewWidth / 2) - (viewWidth / 100 * 30), viewHeight - (viewHeight / 100 * 60),
                (viewWidth / 2) + (viewWidth / 100 * 30), viewHeight - (viewHeight / 100 * 20));

        happyOval.set((viewWidth / 2) - (viewWidth / 100 * 35), viewHeight - (viewHeight / 100 * 90),
                (viewWidth / 2) + (viewWidth / 100 * 35), viewHeight - (viewHeight / 100 * 20));

        amazingOval.set((viewWidth / 2) - (viewWidth / 100 * 35), viewHeight - (viewHeight / 100 * 90),
                (viewWidth / 2) + (viewWidth / 100 * 35), viewHeight - (viewHeight / 100 * 15));

        tongueOval.set((viewWidth / 2) - (viewWidth / 100 * 20), viewHeight - (viewHeight / 100 * 60),
                (viewWidth / 2) + (viewWidth / 100 * 20), viewHeight - (viewHeight / 100 * 20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draw face BG
        paint.setColor(faceColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(faceBgOval, 0, 180, true, paint);


        switch (whatToDraw) {
            case 0:
                drawSadFace(canvas);
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

    private void drawSadFace(Canvas canvas) {
        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(currEyeLX, currEyeY, eyeRadius, paint);
        canvas.drawCircle(currEyeRX, currEyeY, eyeRadius, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(sadOval, 0, -180, false, paint);

    }

    private void drawNeutralFace(Canvas canvas) {

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(currEyeLX, currEyeY, eyeRadius, paint);
        canvas.drawCircle(currEyeRX, currEyeY, eyeRadius, paint);


        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //canvas.drawArc(neutralOval, 0, 180, false, paint);
        canvas.drawLine((viewWidth / 2) - (viewWidth / 100) * 30,
                viewHeight - (viewHeight / 100) * 30,
                (viewWidth / 2) + (viewWidth / 100) * 30,
                viewHeight - (viewHeight / 100) * 30,paint);

    }

    private void drawSlightSmileFace(Canvas canvas) {

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(currEyeLX, currEyeY, eyeRadius, paint);
        canvas.drawCircle(currEyeRX, currEyeY, eyeRadius, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(slightHappyOval, 0, 180, false, paint);

    }

    private void drawHappyFace(Canvas canvas) {

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(currEyeLX, currEyeY, eyeRadius, paint);
        canvas.drawCircle(currEyeRX, currEyeY, eyeRadius, paint);

        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(happyOval, 0, 180, false, paint);

    }

    private void drawAmazingFace(Canvas canvas) {

        //Draw Eyes
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(currEyeLX, currEyeY, eyeRadius, paint);
        canvas.drawCircle(currEyeRX, currEyeY, eyeRadius, paint);


        //Draw mouth
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(amazingOval, 0, 180, true, paint);

        //Draw tongue
        paint.setColor(tongueColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(tongueOval, 0, 180, true, paint);

    }

    public void setSmiley(float rating) {
        switch ((int) rating) {
            case 0:
                whatToDraw = 0;
                startEyesAnimation((viewWidth / 2) - (viewWidth / 100 * 25),
                        (viewWidth / 2) + (viewWidth / 100 * 25),
                        (viewHeight / 100 * 20));
                break;
            case 1:
                whatToDraw = 1;
                startEyesAnimation((viewWidth / 2) - (viewWidth / 100 * 20),
                        (viewWidth / 2) + (viewWidth / 100 * 20),
                        (viewHeight / 100 * 20));
                break;
            case 2:
                whatToDraw = 2;
                startEyesAnimation((viewWidth / 2) - (viewWidth / 100 * 17),
                        (viewWidth / 2) + (viewWidth / 100 * 17),
                        (viewHeight / 100 * 25));
                break;
            case 3:
                whatToDraw = 3;
                startEyesAnimation((viewWidth / 2) - (viewWidth / 100 * 19),
                        (viewWidth / 2) + (viewWidth / 100 * 19),
                        (viewHeight / 100 * 22));
                break;
            case 4:
                whatToDraw = 4;
                startEyesAnimation((viewWidth / 2) - (viewWidth / 100 * 23),
                        (viewWidth / 2) + (viewWidth / 100 * 23),
                        (viewHeight / 100 * 23));
                break;
        }
    }

    private void startEyesAnimation(int... newPositions) {

        leftEyeAnimatorX.setIntValues(currEyeLX, newPositions[0]);
        leftEyeAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currEyeLX = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rightEyeAnimatorX.setIntValues(currEyeRX, newPositions[1]);
        rightEyeAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currEyeRX = (int) animation.getAnimatedValue();
            }
        });
        eyesAnimatorY.setIntValues(currEyeY, newPositions[2]);
        eyesAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currEyeY = (int) animation.getAnimatedValue();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(animationDuration);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(rightEyeAnimatorX, leftEyeAnimatorX, eyesAnimatorY);
        animatorSet.start();
    }

}
