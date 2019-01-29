package com.lbxtech.androidplay.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object PermissionUtil {

    private var permissionCallBack: ((Boolean) -> Unit)? = null
    private var mRequestCode = -1

    fun requestPermissions(
        activity: Activity,
        requestCode: Int,
        permissions: Array<String>,
        callback: (success: Boolean) -> Unit
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callback.invoke(true)
            return
        }

        val list = ArrayList<String>()
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_DENIED) {
                list.add(it)
            }
        }

        if (list.size == 0) {
            callback.invoke(true)
            return
        }

        mRequestCode = requestCode
        permissionCallBack = callback

        ActivityCompat.requestPermissions(activity, list.toTypedArray(), requestCode)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == mRequestCode) {
            permissionCallBack?.invoke(if (grantResults.isEmpty()) false else {
                grantResults.none {
                    it != PackageManager.PERMISSION_GRANTED
                }
            })
        }
    }

}