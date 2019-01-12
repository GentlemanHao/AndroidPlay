package com.lbxtech.androidplay.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lbxtech.androidplay.utils.AppManager

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppManager.instence.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        AppManager.instence.finishActivity(this)
    }

}