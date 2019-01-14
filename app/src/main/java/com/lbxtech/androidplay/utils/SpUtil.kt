package com.lbxtech.androidplay.utils

import android.content.Context
import android.content.SharedPreferences
import com.lbxtech.androidplay.app.App


object SpUtil {

    private val sp: SharedPreferences by lazy {
        App.context.getSharedPreferences("config", Context.MODE_PRIVATE)
    }

    fun putBoolean(context: Context, key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {
        return sp.getBoolean(key, defValue)
    }

    fun putString(context: Context, key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(context: Context, key: String, defValue: String): String? {
        return sp.getString(key, defValue)
    }

    fun putInt(context: Context, key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String, defValue: Int): Int {
        return sp.getInt(key, defValue)
    }

    fun delete(context: Context, key: String) {
        sp.edit().remove(key).apply()
    }

}