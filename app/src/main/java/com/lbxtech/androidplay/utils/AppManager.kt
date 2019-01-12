package com.lbxtech.androidplay.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

class AppManager private constructor() {

    private val activityStack = Stack<Activity>()

    companion object {
        val instence: AppManager by lazy { AppManager() }
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    fun currentActivity() = activityStack.lastElement()

    fun finishAllActivity() {
        activityStack.forEach { it.finish() }
        activityStack.clear()
    }

    fun exitApp(context: Context) {
        finishAllActivity()
        (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
            .killBackgroundProcesses(context.packageName)
        System.exit(0)
    }

}