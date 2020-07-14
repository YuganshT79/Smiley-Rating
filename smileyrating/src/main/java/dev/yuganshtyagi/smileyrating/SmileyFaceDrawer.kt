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

    private val config: SmileyViewConfig = SmileyViewConfig(context, attributeSet)
    private val animator = SmileyAnimator(config)

    fun drawFace(canvas: Canvas) {
        config.paint.color = config.faceColor
        config.paint.isAntiAlias = true
        config.paint.style = Style.FILL
        canvas.drawArc(config.faceBgRect, 0f, 180f, true, config.paint)

        drawEyes(canvas)
        drawFaceForState(canvas, SmileyState.of(config.defaultRating))
    }

    fun onMeasure(width: Int, height: Int) {
        config.onMeasure(width, height)
        animator.updateEyesValues()
    }

    fun updateRating(rating: Float, invalidate: () -> Unit) {
        config.defaultRating = rating.toInt()
        animator.animateEyesToNewPos(invalidate)
    }

    private fun drawFaceForState(canvas: Canvas, state: SmileyState) {
        //update paint
        config.paint.color = config.mouthColor
        config.paint.style = Style.STROKE
        when (state) {
            Sad -> drawSadFace(canvas)
            Neutral -> drawNeutralFace(canvas)
            Okay, Happy -> drawHappyFace(canvas, state)
            Amazing -> drawAmazingFace(canvas)
        }
    }

    private fun drawEyes(canvas: Canvas) {
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
    }

    private fun drawSadFace(canvas: Canvas) {
        canvas.drawArc(config.sadFaceRect, 0f, -180f, false, config.paint)
    }

    private fun drawNeutralFace(canvas: Canvas) {
        canvas.drawLine(
            config.neutralFaceRect.left,
            config.neutralFaceRect.top,
            config.neutralFaceRect.right,
            config.neutralFaceRect.bottom,
            config.paint
        )
    }

    private fun drawHappyFace(canvas: Canvas, state: SmileyState) {
        val rect = if (state is Okay) config.okayFaceRect else config.happyFaceRect
        canvas.drawArc(rect, 0f, 180f, false, config.paint)
    }

    private fun drawAmazingFace(canvas: Canvas) {
        //Draw mouth
        config.paint.style = Style.FILL
        config.paint.color = config.mouthColor
        canvas.drawArc(config.amazingFaceRect, 0f, 180f, true, config.paint)

        //Draw tongue
        config.paint.color = config.tongueColor
        canvas.drawArc(config.tongueRect, 0f, 180f, false, config.paint)
    }
}
