package dev.yuganshtyagi.smileyrating

import android.content.Context
import android.graphics.Canvas
import android.os.Build.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by Yugansh Tyagi on 4/19/2018.
 */
class SmileyRatingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleInt: Int = 0
) : View(context, attributeSet, defStyleInt) {

    private val drawer: SmileyFaceDrawer

    init {
        if (VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
        drawer = SmileyFaceDrawer(context, attributeSet)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        drawer.onMeasure(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawer.drawFace(canvas)
    }

    fun setSmiley(rating: Float) {
        drawer.updateRating(rating)
        invalidate()
    }
}
