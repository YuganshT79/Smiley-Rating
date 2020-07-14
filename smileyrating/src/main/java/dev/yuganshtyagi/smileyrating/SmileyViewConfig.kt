package dev.yuganshtyagi.smileyrating

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import com.yugansh.tyagi.smileyrating.R
import dev.yuganshtyagi.smileyrating.SmileyState.*

/**
 *  Created by Yugansh on 7/12/2020
 */
internal class SmileyViewConfig(
    private val context: Context,
    private val attributeSet: AttributeSet?
) {

    //paint
    val paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 30F.toDp()
    }

    //colors
    var faceColor = 0
    var eyesColor = 0
    var mouthColor = 0
    var tongueColor = 0

    //rect
    var faceBgOval = RectF()
    var sadOval = RectF()
    var slightHappyOval = RectF()
    var happyOval = RectF()
    var amazingOval = RectF()
    var tongueOval = RectF()
    val eyeRadius = 35F.toDp()

    //eyesPos
    var currEyeLX = 0
    var currEyeRX = 0
    var currEyeY = 0

    var defaultRating = 0

    var viewWidth = 0
    var viewHeight = 0
    var widthCenter = 0
    private var heightCenter = 0
    private var centerOffset = 0

    init {
        initAttributeValues()
    }

    private fun initAttributeValues() {
        var typedArray: TypedArray? = null
        try {
            typedArray = context.obtainStyledAttributes(
                attributeSet, R.styleable.SmileyRatingView
            )
            faceColor = typedArray.getColor(
                R.styleable.SmileyRatingView_face_color,
                ContextCompat.getColor(context, R.color.faceColor)
            )

            eyesColor = typedArray.getColor(
                R.styleable.SmileyRatingView_eyes_color,
                ContextCompat.getColor(context, R.color.eyesColor)
            )

            mouthColor = typedArray.getColor(
                R.styleable.SmileyRatingView_mouth_color,
                ContextCompat.getColor(context, R.color.mouthColor)
            )

            tongueColor = typedArray.getColor(
                R.styleable.SmileyRatingView_tongue_color,
                ContextCompat.getColor(context, R.color.tongueColor)
            )

            defaultRating = typedArray.getInteger(
                R.styleable.SmileyRatingView_default_rating, 2
            )
        } catch (exception: Exception) {
            Log.e("Smiley Rating", exception.localizedMessage, exception)
        } finally {
            typedArray?.recycle()
        }
    }

    fun onMeasure(width: Int, height: Int) {
        viewWidth = width
        viewHeight = height
        widthCenter = viewWidth / 2
        heightCenter = viewHeight / 2
        centerOffset = 120.toDp()
        initRectValues()
        updateCurrentEyePos()
    }

    fun updateCurrentEyePos() {
        val position = getEyePosForState(SmileyState.of(defaultRating))
        currEyeLX = position.leftEyeX
        currEyeRX = position.rightEyeX
        currEyeY = position.eyesY
    }

    private fun initRectValues() {
        faceBgOval.set(
            -centerOffset.toFloat(),
            -viewHeight.toFloat(),
            viewWidth + centerOffset.toFloat(),
            viewHeight.toFloat()
        )
        sadOval.set(
            widthCenter - 90F.toDp(),
            viewHeight - 180F.toDp(),
            widthCenter + 90F.toDp(),
            viewHeight - 20F.toDp()
        )
        slightHappyOval.set(
            widthCenter - 110F.toDp(),
            viewHeight - 250F.toDp(),
            widthCenter + 110F.toDp(),
            viewHeight - 70F.toDp()
        )
        happyOval.set(
            widthCenter - 130F.toDp(),
            viewHeight - 330F.toDp(),
            widthCenter + 130F.toDp(),
            viewHeight - 70F.toDp()
        )
        amazingOval.set(
            widthCenter - 132F.toDp(),
            viewHeight - 330F.toDp(),
            widthCenter + 132F.toDp(),
            viewHeight - 50F.toDp()
        )
        tongueOval.set(
            widthCenter - 70F.toDp(),
            viewHeight - 220F.toDp(),
            widthCenter + 70F.toDp(),
            viewHeight - 75F.toDp()
        )
    }

    private fun getEyePosForState(state: SmileyState): EyePos {
        return when (state) {
            Sad -> {
                EyePos(
                    leftEyeX = widthCenter - 90.toDp(),
                    rightEyeX = widthCenter + 90.toDp(),
                    eyesY = 80.toDp()
                )
            }
            Neutral -> {
                EyePos(
                    leftEyeX = widthCenter - 80.toDp(),
                    rightEyeX = widthCenter + 80.toDp(),
                    eyesY = 80.toDp()
                )
            }
            Okay -> {
                EyePos(
                    leftEyeX = widthCenter - 70.toDp(),
                    rightEyeX = widthCenter + 70.toDp(),
                    eyesY = 90.toDp()
                )
            }
            Happy -> {
                EyePos(
                    leftEyeX = widthCenter - 72.toDp(),
                    rightEyeX = widthCenter + 72.toDp(),
                    eyesY = 85.toDp()
                )
            }
            Amazing -> {
                EyePos(
                    leftEyeX = widthCenter - 82.toDp(),
                    rightEyeX = widthCenter + 82.toDp(),
                    eyesY = 72.toDp()
                )
            }
        }
    }

    private data class EyePos(
        var leftEyeX: Int = 0,
        val rightEyeX: Int = 0,
        val eyesY: Int = 0
    )
}