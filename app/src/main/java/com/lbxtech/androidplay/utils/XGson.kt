package com.lbxtech.androidplay.utils

import com.google.gson.Gson

object XGson {
    private val gson = Gson()

    fun <T> parse(content: String, clazz: Class<T>): T {
        return gson.fromJson<T>(content, clazz)
    }

    fun <T> toJson(t: T): String {
        return gson.toJson(t)
    }
}