package com.lbxtech.androidplay.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        lateinit var context: Context
    }
}