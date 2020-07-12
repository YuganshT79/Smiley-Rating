package dev.yuganshtyagi.smileyrating

/**
 *  Created by Yugansh on 7/12/2020
 */
sealed class SmileyState(val value: Int) {
    object Sad : SmileyState(0)
    object Neutral : SmileyState(1)
    object Okay : SmileyState(2)
    object Happy : SmileyState(3)
    object Amazing : SmileyState(4)
}