package dev.yuganshtyagi.smileyrating

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint.Cap
import android.graphics.Paint.Style
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.yugansh.tyagi.smileyrating.R

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
class SmileyRatingView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleInt: Int = 0
) : View(context, attributeSet, defStyleInt) {

    private var centerOffset = 0
    private var viewWidth = 0
    private var viewHeight = 0
    private var currEyeLX = 0
    private var currEyeRX = 0
    private var currEyeY = 0
    private var rightEyeAnimatorX: ValueAnimator
    private var leftEyeAnimatorX: ValueAnimator
    private var eyesAnimatorY: ValueAnimator

    private val config: SmileyViewConfig

    init {
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }

        leftEyeAnimatorX = ValueAnimator()
        rightEyeAnimatorX = ValueAnimator()
        eyesAnimatorY = ValueAnimator()

        val typedArray: TypedArray = context
                .obtainStyledAttributes(attributeSet, R.styleable.SmileyRatingView)

        config = SmileyViewConfig(context, typedArray)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = measuredWidth
        viewHeight = measuredHeight
        centerOffset = viewHeight / 3
        val widthCenter = viewWidth / 2
        setSmiley(config.defaultRating.toFloat())
//        when (config.defaultRating) {
//            0 -> {
//                currEyeLX = widthCenter - 90.toDp()
//                currEyeRX = widthCenter + 90.toDp()
//                currEyeY = 80.toDp()
////                currEyeLX = viewWidth / 2 - viewWidth / 100 * 25
////                currEyeRX = viewWidth / 2 + viewWidth / 100 * 25
////                currEyeY = viewHeight / 100 * 20
//            }
//            1 -> {
//                currEyeLX = widthCenter - 80.toDp()
//                currEyeRX = widthCenter + 80.toDp()
//                currEyeY = 80.toDp()
////                currEyeLX = widthCenter - viewWidth / 100 * 20
////                currEyeRX = widthCenter + viewWidth / 100 * 20
////                currEyeY = viewHeight / 100 * 20
//            }
//            2 -> {
//                currEyeLX = widthCenter - 70.toDp()
//                currEyeRX = widthCenter + 70.toDp()
//                currEyeY = 90.toDp()
////                currEyeLX = widthCenter - viewWidth / 100 * 17
////                currEyeRX = widthCenter + viewWidth / 100 * 17
////                currEyeY = viewHeight / 100 * 25
//            }
//            3 -> {
//                currEyeLX = widthCenter - 75.toDp()
//                currEyeRX = widthCenter + 75.toDp()
//                currEyeY = 85.toDp()
////                currEyeLX = widthCenter - viewWidth / 100 * 19
////                currEyeRX = widthCenter + viewWidth / 100 * 19
////                currEyeY = viewHeight / 100 * 22
//            }
//            4 -> {
//                currEyeLX = widthCenter - 82.toDp()
//                currEyeRX = widthCenter + 82.toDp()
//                currEyeY = 72.toDp()
////                currEyeLX = widthCenter - viewWidth / 100 * 23
////                currEyeRX = widthCenter + viewWidth / 100 * 23
////                currEyeY = viewHeight / 100 * 23
//            }
//        }
        config.onMeasure(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Draw face BG


        config.paint.color = config.faceColor
        config.paint.isAntiAlias = true
        config.paint.style = Style.FILL
        canvas.drawArc(config.faceBgOval, 0f, 180f, true, config.paint)
        when (config.defaultRating) {
            0 -> drawSadFace(canvas)
            1 -> drawNeutralFace(canvas)
            2 -> drawSlightSmileFace(canvas)
            3 -> drawHappyFace(canvas)
            4 -> drawAmazingFace(canvas)
        }
    }

    private fun drawSadFace(canvas: Canvas) {
        //Draw Eyes

        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)

        //Draw mouth


        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        config.paint.strokeCap = Cap.ROUND
        canvas.drawArc(config.sadOval, 0f, -180f, false, config.paint)
    }

    private fun drawNeutralFace(canvas: Canvas) {

        //Draw Eyes

        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)


        //Draw mouth


        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        config.paint.strokeCap = Cap.ROUND

        canvas.drawLine(viewWidth / 2 - viewWidth / 100 * 30.toFloat(),
                viewHeight - viewHeight / 100 * 30.toFloat(),
                viewWidth / 2 + viewWidth / 100 * 30.toFloat(),
                viewHeight - viewHeight / 100 * 30.toFloat(), config.paint)
    }

    private fun drawSlightSmileFace(canvas: Canvas) {

        //Draw Eyes

        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)

        //Draw mouth


        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        config.paint.strokeCap = Cap.ROUND
        canvas.drawArc(config.slightHappyOval, 0f, 180f, false, config.paint)
    }

    private fun drawHappyFace(canvas: Canvas) {

        //Draw Eyes

        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)

        //Draw mouth


        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        config.paint.strokeCap = Cap.ROUND
        canvas.drawArc(config.happyOval, 0f, 180f, false, config.paint)
    }

    private fun drawAmazingFace(canvas: Canvas) {

        //Draw Eyes

        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(currEyeLX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)
        canvas.drawCircle(currEyeRX.toFloat(), currEyeY.toFloat(), config.eyeRadius, config.paint)


        //Draw mouth


        config.paint.color = config.mouthColor
        config.paint.style = Style.FILL
        canvas.drawArc(config.amazingOval, 0f, 180f, true, config.paint)

        //Draw tongue


        config.paint.color = config.tongueColor
        config.paint.style = Style.FILL
        canvas.drawArc(config.tongueOval, 0f, 180f, false, config.paint)
    }

    fun setSmiley(rating: Float) {
        when (rating.toInt()) {
            0 -> {
                config.defaultRating = 0
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 25,
                        viewWidth / 2 + viewWidth / 100 * 25,
                        viewHeight / 100 * 20)
            }
            1 -> {
                config.defaultRating = 1
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 20,
                        viewWidth / 2 + viewWidth / 100 * 20,
                        viewHeight / 100 * 20)
            }
            2 -> {
                config.defaultRating = 2
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 17,
                        viewWidth / 2 + viewWidth / 100 * 17,
                        viewHeight / 100 * 25)
            }
            3 -> {
                config.defaultRating = 3
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 19,
                        viewWidth / 2 + viewWidth / 100 * 19,
                        viewHeight / 100 * 22)
            }
            4 -> {
                config.defaultRating = 4
                startEyesAnimation(viewWidth / 2 - viewWidth / 100 * 23,
                        viewWidth / 2 + viewWidth / 100 * 23,
                        viewHeight / 100 * 23)
            }
        }
    }

    private fun startEyesAnimation(vararg newPositions: Int) {
        leftEyeAnimatorX.setIntValues(currEyeLX, newPositions[0])
        leftEyeAnimatorX.addUpdateListener { animation ->
            currEyeLX = animation.animatedValue as Int
            invalidate()
        }
        rightEyeAnimatorX.setIntValues(currEyeRX, newPositions[1])
        rightEyeAnimatorX.addUpdateListener { animation -> currEyeRX = animation.animatedValue as Int }
        eyesAnimatorY.setIntValues(currEyeY, newPositions[2])
        eyesAnimatorY.addUpdateListener { animation -> currEyeY = animation.animatedValue as Int }
        val animatorSet = AnimatorSet()
        animatorSet.duration = SmileyViewConfig.animationDuration
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.playTogether(rightEyeAnimatorX, leftEyeAnimatorX, eyesAnimatorY)
        animatorSet.start()
    }
}