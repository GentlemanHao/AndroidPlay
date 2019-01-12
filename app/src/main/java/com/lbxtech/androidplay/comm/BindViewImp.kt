package com.lbxtech.androidplay.comm

import android.app.Activity
import android.view.View

class BindViewImp {

    companion object {

        fun initBindView(activity: Activity) {
            val clazz = activity.javaClass
            for (field in clazz.declaredFields) {
                val annotations = field.getAnnotation(BindView::class.java)
                if (annotations != null) {
                    val view = activity.findViewById<View>(annotations.value)
                    field.isAccessible = true
                    field.set(activity, view)
                }
            }
        }
    }
}