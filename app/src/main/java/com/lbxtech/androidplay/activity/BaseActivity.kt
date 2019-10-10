package com.lbxtech.androidplay.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lbxtech.androidplay.app.AppManager
import com.lbxtech.androidplay.base.initBindView
import com.lbxtech.androidplay.receiver.NetworkStateReceiver
import com.lbxtech.androidplay.utils.PermissionUtil

abstract class BaseActivity : AppCompatActivity() {

    private var networkStateReceiver: NetworkStateReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initBindView(this)

        onBindView()

        AppManager.instance.addActivity(this)
    }

    protected fun registerNetworkReceiver() {
        networkStateReceiver = NetworkStateReceiver()
        registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    abstract fun getLayoutId(): Int

    abstract fun onBindView()

    override fun onDestroy() {
        super.onDestroy()

        networkStateReceiver?.run { unregisterReceiver(this) }

        AppManager.instance.finishActivity(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}