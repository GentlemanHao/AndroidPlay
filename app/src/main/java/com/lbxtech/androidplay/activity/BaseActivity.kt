package com.lbxtech.androidplay.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lbxtech.androidplay.app.AppManager
import com.lbxtech.androidplay.base.initBindView
import com.lbxtech.androidplay.utils.PermissionUtil

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initBindView(this)

        onBindView()

        AppManager.instence.addActivity(this)
    }

    abstract fun getLayoutId(): Int

    abstract fun onBindView()

    override fun onDestroy() {
        super.onDestroy()

        AppManager.instence.finishActivity(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}