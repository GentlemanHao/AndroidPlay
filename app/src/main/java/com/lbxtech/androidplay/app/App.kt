package com.lbxtech.androidplay.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        initUMPushSdk()
    }

    private fun initUMPushSdk() {
        UMConfigure.init(this, "5f054344895cca96f0000083", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "b7cf21d2dc55678f7f527ea1acf006d5")
        val pushAgent = PushAgent.getInstance(this)
        pushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String?) {
                Log.d("--wh--", "deviceToken: $deviceToken")
            }

            override fun onFailure(p0: String?, p1: String?) {
                Log.d("--wh--", "p0: $p0  p1: $p1")
            }
        })
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        lateinit var context: Context
    }
}