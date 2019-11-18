package com.lbxtech.androidplay.utils

import com.lbxtech.androidplay.app.App
import kotlin.math.roundToInt

object XUI {

    private val density = App.context.resources.displayMetrics.density

    fun dpToPx(dpValue: Int): Int {
        return (dpValue * density).roundToInt()
    }
}