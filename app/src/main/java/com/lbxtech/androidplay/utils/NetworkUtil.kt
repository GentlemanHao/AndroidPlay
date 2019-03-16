package com.lbxtech.androidplay.utils

import android.content.Context
import android.net.ConnectivityManager
import com.lbxtech.androidplay.app.App

object NetworkUtil {
    fun isNetworkConnected(): Boolean {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        networkInfo.forEach {
            if (it.isConnected) {
                return true
            }
        }
        return false
    }

    fun getNetworkType(): NetworkType {
        val connectivityManager = App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return if (networkInfo == null) NetworkType.NONE else {
            when (networkInfo.type) {
                ConnectivityManager.TYPE_MOBILE -> NetworkType.MOBILE
                ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                else -> NetworkType.NONE
            }
        }
    }
}

enum class NetworkType {
    NONE, MOBILE, WIFI
}