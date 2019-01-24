package com.lbxtech.androidplay.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lbxtech.androidplay.app.AppManager

object GlideUtil {

    fun load(url: String, view: ImageView) {
        Glide.with(AppManager.instence.currentActivity()).load(url).into(view)
    }

}