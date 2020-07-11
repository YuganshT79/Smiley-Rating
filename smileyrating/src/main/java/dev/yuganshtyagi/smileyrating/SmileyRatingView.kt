package dev.yuganshtyagi.smileyrating

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Paint.Style
import android.graphics.RectF
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.yugansh.tyagi.smileyrating.R.color
import com.yugansh.tyagi.smileyrating.R.styleable

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
class SmileyRatingView : View {
    private var faceColor = 0
    private var eyesColor = 0
    private var mouthColor = 0
    private var tongueColor = 0
    private var faceBgOval: RectF? = null
    private var sadOval: RectF? = null
    private var neutralOval: RectF? = null
    private var slightHappyOval: RectF? = null
    private var happyOval: RectF? = null
    private var amazingOval: RectF? = null
    private var tongueOval: RectF? = null
    private var paint: Paint? = null
    private var centerOffset = 0
    private var viewWidth = 0
    private var viewHeight = 0
    private var whatToDraw = 2
    private var defaultRating = 0
    private var strokeWidth = 0
    private var eyeRadius = 0
    private var currEyeLX = 0
    private var currEyeRX = 0
    private var currEyeY = 0
    private var rightEyeAnimatorX: ValueAnimator? = null
    private var leftEyeAnimatorX: ValueAnimator? = null
    private var eyesAnimatorY: ValueAnimator? = null
    private val animationDuration: Long = 300

    constructor(context: Context?) : super(context) {
        //Disable Hardware acceleration on device with API < 18
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint = Paint()
        faceBgOval = RectF()
        sadOval = RectF()
        neutralOval = RectF()
        slightHappyOval = RectF()
        happyOval = RectF()
        amazingOval = RectF()
        tongueOval = RectF()
        leftEyeAnimatorX = ValueAnimator()
        rightEyeAnimatorX = ValueAnimator()
        eyesAnimatorY = ValueAnimator()
        //Getting attributes value
        val typedArray: TypedArray = getContext().obtainStyledAttributes(attrs, styleable.SmileyRatingView)
        try {
            faceColor = typedArray.getColor(styleable.SmileyRatingView_face_color,
                    resources.getColor(color.faceColor))
            eyesColor = typedArray.getColor(styleable.SmileyRatingView_eyes_color,
                    resources.getColor(color.eyesColor))
            mouthColor = typedArray.getColor(styleable.SmileyRatingView_mouth_color,
                    resources.getColor(color.mouthColor))
            tongueColor = typedArray.getColor(styleable.SmileyRatingView_tongue_color,
                    resources.getColor(color.tongueColor))
            defaultRating = typedArray.getInteger(
                    styleable.SmileyRatingView_default_rating, 2)
        } finally {
            typedArray.recycle()
        }
        whatToDraw = defaultRating
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = measuredWidth
        viewHeight = measuredHeight
        strokeWidth = viewHeight / 30 + viewWidth / 30
        eyeRadius = viewHeight / 25 + viewWidth / 25
        centerOffset = viewHeight / 3
        when (whatToDraw) {
            0 -> {
                currEyeLX = viewWidth / 2 - viewWidth / 100 * 25
                currEyeRX = viewWidth / 2 + viewWidth / 100 * 25
                currEyeY = viewHeight / 100 * 20
            }
            1 -> {
                currEyeLX = viewWidth / 2 - viewWidth / 100 * 20
                currEyeRX = viewWidth / 2 + viewWidth / 100 * 20
                currEyeY = viewHeight / 100 * 20
            }
            2 -> {
                currEyeLX = viewWidth / 2 - viewWidth / 100 * 17
                currEyeRX = viewWidth / 2 + viewWidth / 100 * 17
                currEyeY = viewHeight / 100 * 25
            }
            3 -> {
                currEyeLX = viewWidth / 2 - viewWidth / 100 * 19
                currEyeRX = viewWidth / 2 + viewWidth / 100 * 19
                currEyeY = viewHeight / 100 * 22
            }
            4 -> {
                currEyeLX = viewWidth / 2 - viewWidth / 100 * 23
                currEyeRX = viewWidth / 2 + viewWidth / 100 * 23
                currEyeY = viewHeight / 100 * 23
            }
        }
        faceBgOval!!.set(-centerOffset.toFloat(), -viewHeight.toFloat(), viewWidth + centerOffset.toFloat(), viewHeight.toFloat())
        sadOval!!.set(viewWidth / 2 - (viewWidth / 100 * 25).toFloat(), viewHeight - (viewHeight / 100 * 45).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 25).toFloat(), viewHeight - (viewHeight / 100 * 10).toFloat())

//        neutralOval.set((viewWidth / 2) - (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40,
//                (viewWidth / 2) + (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40);


        slightHappyOval!!.set(viewWidth / 2 - (viewWidth / 100 * 30).toFloat(), viewHeight - (viewHeight / 100 * 60).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 30).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
        happyOval!!.set(viewWidth / 2 - (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 90).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
        amazingOval!!.set(viewWidth / 2 - (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 90).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 15).toFloat())
        tongueOval!!.set(viewWidth / 2 - (viewWidth / 100 * 20).toFloat(), viewHeight - (viewHeight / 100 * 60).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 20).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Draw face BG


        paint!!.color = faceColor
        paint!!.isAntiAlias = true
        paint!!.style = Style.FILL
        canvas.drawArc(faceBgOval!!, 0f, 180f, true, paint!!)
        when (whatToDraw) {
            0 -> drawSadFace(canvas)
            1 -> drawNeutralFace(canvas)
            2 -> drawSlightSmileFace(canvas)
            3 -> drawHappyFace(canvas)
            4 -> drawAmazingFace(canvas)
        }
    }

    private fun drawSadFace(canvas: Canvas) {
        //Draw Eyes

        paint!!.color = eyesColor
        paint!!.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)

        //Draw mouth


        paint!!.color = mouthColor
        paint!!.style = Style.STROKE
        paint!!.strokeWidth = strokeWidth.toFloat()
        paint!!.strokeCap = Cap.ROUND
        canvas.drawArc(sadOval!!, 0f, -180f, false, paint!!)
    }

    private fun drawNeutralFace(canvas: Canvas) {

        //Draw Eyes

        paint!!.color = eyesColor
        paint!!.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)


        //Draw mouth


        paint!!.color = mouthColor
        paint!!.style = Style.STROKE
        paint!!.strokeWidth = strokeWidth.toFloat()
        paint!!.strokeCap = Cap.ROUND
        //canvas.drawArc(neutralOval, 0, 180, false, paint);


        canvas.drawLine(viewWidth / 2 - viewWidth / 100 * 30.toFloat(),
                viewHeight - viewHeight / 100 * 30.toFloat(),
                viewWidth / 2 + viewWidth / 100 * 30.toFloat(),
                viewHeight - viewHeight / 100 * 30.toFloat(), paint!!)
    }

    private fun drawSlightSmileFace(canvas: Canvas) {

        //Draw Eyes

        paint!!.color = eyesColor
        paint!!.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)

        //Draw mouth


        paint!!.color = mouthColor
        paint!!.style = Style.STROKE
        paint!!.strokeWidth = strokeWidth.toFloat()
        paint!!.strokeCap = Cap.ROUND
        canvas.drawArc(slightHappyOval!!, 0f, 180f, false, paint!!)
    }

    private fun drawHappyFace(canvas: Canvas) {

        //Draw Eyes

        paint!!.color = eyesColor
        paint!!.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)

        //Draw mouth


        paint!!.color = mouthColor
        paint!!.style = Style.STROKE
        paint!!.strokeWidth = strokeWidth.toFloat()
        paint!!.strokeCap = Cap.ROUND
        canvas.drawArc(happyOval!!, 0f, 180f, false, paint!!)
    }

    private fun drawAmazingFace(canvas: Canvas) {

        //Draw Eyes

        paint!!.color = eyesColor
        paint!!.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), eyeRadius.toFloat(), paint!!)


        //Draw mouth


        paint!!.color = mouthColor
        paint!!.style = Style.FILL
        canvas.drawArc(amazingOval!!, 0f, 180f, true, paint!!)

        //Draw tongue


        paint!!.color = tongueColor
        paint!!.style = Style.FILL
        canvas.drawArc(tongueOval!!, 0f, 180f, true, paint!!)
    }

    fun setSmiley(rating: Float) {
        when (rating.toInt()) {
            0 -> {
                whatToDraw = 0
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 25,
                        viewWidth / 2 + viewWidth / 100 * 25,
                        viewHeight / 100 * 20)
            }
            1 -> {
                whatToDraw = 1
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 20,
                        viewWidth / 2 + viewWidth / 100 * 20,
                        viewHeight / 100 * 20)
            }
            2 -> {
                whatToDraw = 2
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 17,
                        viewWidth / 2 + viewWidth / 100 * 17,
                        viewHeight / 100 * 25)
            }
            3 -> {
                whatToDraw = 3
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 19,
                        viewWidth / 2 + viewWidth / 100 * 19,
                        viewHeight / 100 * 22)
            }
            4 -> {
                whatToDraw = 4
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 23,
                        viewWidth / 2 + viewWidth / 100 * 23,
                        viewHeight / 100 * 23)
            }
        }
    }

    private fun startEyesAnimation(vararg newPositions: Int) {
        leftEyeAnimatorX!!.setIntValues(currEyeLX, newPositions[0])
        leftEyeAnimatorX!!.addUpdateListener { animation ->
            currEyeLX = animation.animatedValue as Int
            invalidate()
        }
        rightEyeAnimatorX!!.setIntValues(currEyeRX, newPositions[1])
        rightEyeAnimatorX!!.addUpdateListener { animation -> currEyeRX = animation.animatedValue as Int }
        eyesAnimatorY!!.setIntValues(currEyeY, newPositions[2])
        eyesAnimatorY!!.addUpdateListener { animation -> currEyeY = animation.animatedValue as Int }
        val animatorSet = AnimatorSet()
        animatorSet.duration = animationDuration
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.playTogether(rightEyeAnimatorX, leftEyeAnimatorX, eyesAnimatorY)
        animatorSet.start()
    }
}