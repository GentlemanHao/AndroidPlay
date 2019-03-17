package com.lbxtech.androidplay.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import com.lbxtech.androidplay.utils.NetworkType
import com.lbxtech.androidplay.utils.NetworkUtil

class NetworkStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("--wh--", "------------")
        if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            when (NetworkUtil.getNetworkType()) {
                NetworkType.NONE -> Toast.makeText(context, "nnnnnnnnnnn", Toast.LENGTH_SHORT).show()
                NetworkType.MOBILE -> Toast.makeText(context, "mmmmmmmmmmm", Toast.LENGTH_SHORT).show()
                NetworkType.WIFI -> Toast.makeText(context, "wwwwwwwww", Toast.LENGTH_SHORT).show()
            }
        }
    }
}