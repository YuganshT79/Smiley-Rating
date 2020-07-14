package dev.yuganshtyagi.smileyrating

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint.*
import android.util.AttributeSet
import dev.yuganshtyagi.smileyrating.SmileyState.*

/**
 * Created by Yugansh on 14/07/20.
 */
internal class SmileyFaceDrawer(context: Context, attributeSet: AttributeSet?) {

    private var config: SmileyViewConfig = SmileyViewConfig(context, attributeSet)
    private var animator: SmileyFaceAnimator = SmileyFaceAnimator(config)

    fun drawFace(canvas: Canvas) {
        config.paint.color = config.faceColor
        config.paint.isAntiAlias = true
        config.paint.style = Style.FILL
        canvas.drawArc(config.faceBgRect, 0f, 180f, true, config.paint)

        when (SmileyState.of(config.defaultRating)) {
            Sad -> drawSadFace(canvas)
            Neutral -> drawNeutralFace(canvas)
            Okay -> drawOkayFace(canvas)
            Happy -> drawHappyFace(canvas)
            Amazing -> drawAmazingFace(canvas)
        }
    }

    fun onMeasure(width: Int, height: Int) {
        config.onMeasure(width, height)
    }

    fun updateRating(rating: Float) {
        config.defaultRating = rating.toInt()
        config.updateCurrentEyePos()
    }

    private fun drawSadFace(canvas: Canvas) {
        //Draw Eyes
        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(
            config.currEyeLX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )
        canvas.drawCircle(
            config.currEyeRX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )

        //Draw mouth
        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        canvas.drawArc(config.sadFaceRect, 0f, -180f, false, config.paint)
    }

    private fun drawNeutralFace(canvas: Canvas) {
        //Draw Eyes
        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(
            config.currEyeLX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )
        canvas.drawCircle(
            config.currEyeRX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )

        //Draw mouth
        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        canvas.drawLine(
            config.neutralFaceRect.left,
            config.neutralFaceRect.top,
            config.neutralFaceRect.right,
            config.neutralFaceRect.bottom,
            config.paint
        )
    }

    private fun drawOkayFace(canvas: Canvas) {
        //Draw Eyes
        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(
            config.currEyeLX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )
        canvas.drawCircle(
            config.currEyeRX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )

        //Draw mouth
        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        canvas.drawArc(config.okayFaceRect, 0f, 180f, false, config.paint)
    }

    private fun drawHappyFace(canvas: Canvas) {
        //Draw Eyes
        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(
            config.currEyeLX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )
        canvas.drawCircle(
            config.currEyeRX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )

        //Draw mouth
        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        canvas.drawArc(config.happyFaceRect, 0f, 180f, false, config.paint)
    }

    private fun drawAmazingFace(canvas: Canvas) {
        //Draw Eyes
        config.paint.color = config.eyesColor
        config.paint.style = Style.FILL
        canvas.drawCircle(
            config.currEyeLX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )
        canvas.drawCircle(
            config.currEyeRX.toFloat(),
            config.currEyeY.toFloat(),
            config.eyeRadius,
            config.paint
        )

        //Draw mouth
        config.paint.color = config.mouthColor
        config.paint.style = Style.FILL
        canvas.drawArc(config.amazingFaceRect, 0f, 180f, true, config.paint)

        //Draw tongue
        config.paint.color = config.tongueColor
        config.paint.style = Style.FILL
        canvas.drawArc(config.tongueRect, 0f, 180f, false, config.paint)
    }
}