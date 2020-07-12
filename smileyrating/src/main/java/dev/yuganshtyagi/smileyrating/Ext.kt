package dev.yuganshtyagi.smileyrating

import android.content.res.Resources

/**
 *  Created by Yugansh on 7/12/2020
 */

fun Float.toDp(): Float {
    return this * Resources.getSystem().displayMetrics.density
}

fun Float.toPx(): Float {
    return this / Resources.getSystem().displayMetrics.density
}

fun Int.toDp(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.toPx(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}