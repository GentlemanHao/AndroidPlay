package com.lbxtech.androidplay.utils.http

typealias Callback<T> = (success: Boolean, result: T?) -> Unit

interface IHttpRequest<T> {

    fun setUrl(url: String)

    fun setData(data: ByteArray)

    fun setCallback(callback: Callback<T>)

    fun setClass(clazz: Class<T>)

    fun execute()
}