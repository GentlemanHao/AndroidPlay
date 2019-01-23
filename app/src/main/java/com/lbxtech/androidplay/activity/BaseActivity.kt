package com.lbxtech.androidplay.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lbxtech.androidplay.app.AppManager
import com.lbxtech.androidplay.base.initBindView

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initBindView(this)

        bindView()

        AppManager.instence.addActivity(this)
    }

    abstract fun getLayoutId(): Int

    abstract fun bindView()

    override fun onDestroy() {
        super.onDestroy()

        AppManager.instence.finishActivity(this)
    }

}