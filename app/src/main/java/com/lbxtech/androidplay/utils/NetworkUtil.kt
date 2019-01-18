package com.lbxtech.androidplay.utils

import android.content.Context
import android.net.ConnectivityManager
import com.lbxtech.androidplay.app.App


object NetworkUtil {
    fun isNetworkConnected(): Boolean {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null) {
            return networkInfo.isAvailable
        }
        return false
    }

    fun isWifiConnected(): Boolean {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiNetworkInfo != null) {
            return wifiNetworkInfo.isAvailable
        }
        return false
    }

    fun isMobileConnected(): Boolean {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (mobileNetworkInfo != null) {
            return mobileNetworkInfo.isAvailable
        }
        return false
    }

    fun isNetworkAvailable(): Boolean {
        if (isNetworkConnected()) {
            if (isMobileConnected() || isWifiConnected()) {
                return true
            }
        }
        return false
    }
}