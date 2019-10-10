package com.lbxtech.androidplay.utils

import android.widget.ImageView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.app.AppManager
import com.lbxtech.androidplay.comm.GlideApp

object GlideUtil {

    fun load(url: String?, view: ImageView) {
        GlideApp.with(AppManager.instance.currentActivity()).load(url).placeholder(R.mipmap.image).into(view)
    }

}