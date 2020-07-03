package com.lbxtech.androidplay.base

import android.app.Activity
import androidx.fragment.app.Fragment
import android.view.View

@kotlin.annotation.Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BindView(val value: Int)

fun initBindView(activity: Activity) {
    bindView(activity)
}

fun initBindView(fragment: Fragment) {
    bindView(fragment)
}

private fun bindView(context: Any) {
    val clazz = context.javaClass
    clazz.declaredFields.forEach { field ->
        val annotation = field.getAnnotation(BindView::class.java) ?: return@forEach

        val view = when (context) {
            is Activity -> context.findViewById<View>(annotation.value)
            is Fragment -> context.view?.findViewById<View>(annotation.value)
            else -> null
        } ?: return@forEach

        field.isAccessible = true
        field.set(context, view)
    }
}