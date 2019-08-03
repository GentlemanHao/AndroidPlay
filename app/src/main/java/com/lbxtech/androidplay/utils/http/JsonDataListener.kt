package com.lbxtech.androidplay.utils.http

interface JsonDataListener<T> {
    fun onSuccess(data: T)
}