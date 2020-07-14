package dev.yuganshtyagi.smileyrating

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.animation.DecelerateInterpolator
import dev.yuganshtyagi.smileyrating.SmileyViewConfig.*

/**
 * Created by Yugansh on 15/07/20.
 */
internal class SmileyAnimator(private val config: SmileyViewConfig) {

    private var currLeftEyeX: Int = 0
    private var currRightEyeX: Int = 0
    private var currEyesY: Int = 0

    fun updateEyesValues(newPos: EyePos? = null) {
        if (newPos == null) {
            currLeftEyeX = config.currEyeLX
            currRightEyeX = config.currEyeRX
            currEyesY = config.currEyeY
        } else {
            currLeftEyeX = newPos.leftEyeX
            currRightEyeX = newPos.rightEyeX
            currEyesY = newPos.eyesY
        }
    }

    fun animateEyesToNewPos(invalidate: () -> Unit) {
        val newPos = config.getEyePosForState(SmileyState.of(config.defaultRating))
        val leftEyeXAnimator = getValueAnimator(currLeftEyeX, newPos.leftEyeX)
        leftEyeXAnimator.addUpdateListener {
            config.currEyeLX = it.animatedValue as Int
        }
        val rightEyeXAnimator = getValueAnimator(currRightEyeX, newPos.rightEyeX)
        rightEyeXAnimator.addUpdateListener {
            config.currEyeRX = it.animatedValue as Int
        }
        val eyesYAnimator = getValueAnimator(currEyesY, newPos.eyesY)
        eyesYAnimator.addUpdateListener {
            config.currEyeY = it.animatedValue as Int
            invalidate.invoke()
        }

        val animatorSet = AnimatorSet()
        animatorSet.duration = 220L
        animatorSet.interpolator = DecelerateInterpolator()
        animatorSet.playTogether(leftEyeXAnimator, rightEyeXAnimator, eyesYAnimator)
        animatorSet.start()

        updateEyesValues(newPos)
    }

    private fun getValueAnimator(from: Int, to: Int): ValueAnimator {
        return ValueAnimator.ofInt(from, to)
    }
}
