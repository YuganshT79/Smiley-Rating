package dev.yuganshtyagi.smileyrating

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import androidx.core.content.ContextCompat
import com.yugansh.tyagi.smileyrating.R

/**
 *  Created by Yugansh on 7/12/2020
 */
internal class SmileyViewConfig(
        private val context: Context,
        private val typedArray: TypedArray
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
    var neutralOval = RectF()
    var slightHappyOval = RectF()
    var happyOval = RectF()
    var amazingOval = RectF()
    var tongueOval = RectF()
    val eyeRadius = 35F.toDp()

    var defaultRating = 0

    var viewWidth = 0
    var viewHeight = 0
    var centerOffset = 0

    init {
        initAttributeValues()
    }

    private fun initAttributeValues() {
        try {
            faceColor = typedArray.getColor(R.styleable.SmileyRatingView_face_color,
                    ContextCompat.getColor(context, R.color.faceColor))

            eyesColor = typedArray.getColor(R.styleable.SmileyRatingView_eyes_color,
                    ContextCompat.getColor(context, R.color.eyesColor))

            mouthColor = typedArray.getColor(R.styleable.SmileyRatingView_mouth_color,
                    ContextCompat.getColor(context, R.color.mouthColor))

            tongueColor = typedArray.getColor(R.styleable.SmileyRatingView_tongue_color,
                    ContextCompat.getColor(context, R.color.tongueColor))

            defaultRating = typedArray.getInteger(
                    R.styleable.SmileyRatingView_default_rating, defaultSmileyRating)

        } catch (exception: Exception) {
            Log.e("Smiley Rating", exception.localizedMessage, exception)
        } finally {
            typedArray.recycle()
        }
    }

    fun onMeasure(width: Int, height: Int) {
        viewWidth = width
        viewHeight = height
        centerOffset = 120.toDp()
        initRectValues()
    }


    private fun initRectValues() {
        faceBgOval.set(-centerOffset.toFloat(),
                -viewHeight.toFloat(), viewWidth + centerOffset.toFloat(), viewHeight.toFloat())
        sadOval.set(viewWidth / 2 - (viewWidth / 100 * 25).toFloat(), viewHeight - (viewHeight / 100 * 45).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 25).toFloat(), viewHeight - (viewHeight / 100 * 10).toFloat())

//        neutralOval.set((viewWidth / 2) - (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40,
//                (viewWidth / 2) + (viewWidth / 100) * 30,
//                viewHeight - (viewHeight / 100) * 40);


        slightHappyOval.set(viewWidth / 2 - (viewWidth / 100 * 30).toFloat(), viewHeight - (viewHeight / 100 * 60).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 30).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
        happyOval.set(viewWidth / 2 - (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 90).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
        amazingOval.set(viewWidth / 2 - (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 90).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 35).toFloat(), viewHeight - (viewHeight / 100 * 15).toFloat())
        tongueOval.set(viewWidth / 2 - (viewWidth / 100 * 20).toFloat(), viewHeight - (viewHeight
                / 100 * 60).toFloat(),
                viewWidth / 2 + (viewWidth / 100 * 20).toFloat(), viewHeight - (viewHeight / 100 * 20).toFloat())
    }

    companion object {
        const val animationDuration = 300L
        const val defaultSmileyRating = 2
    }
}