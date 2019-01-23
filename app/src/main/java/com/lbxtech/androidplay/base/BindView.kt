package com.lbxtech.androidplay.base

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View

@kotlin.annotation.Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val value: Int)

fun initBindView(activity: Activity) {
    val clazz = activity.javaClass
    for (field in clazz.declaredFields) {
        val bindView = field.getAnnotation(BindView::class.java)
        if (bindView != null) {
            val view = activity.findViewById<View>(bindView.value)
            field.isAccessible = true
            field.set(activity, view)
        }
    }
}

fun initBindView(fragment: Fragment, rootView: View) {
    val clazz = fragment.javaClass
    for (field in clazz.declaredFields) {
        val bindView = field.getAnnotation(BindView::class.java)
        if (bindView != null) {
            val view = rootView.findViewById<View>(bindView.value)
            field.isAccessible = true
            field.set(fragment, view)
        }
    }
}