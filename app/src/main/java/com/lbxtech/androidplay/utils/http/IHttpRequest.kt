package com.lbxtech.androidplay.utils.http

interface IHttpRequest {

    fun setUrl(url: String)

    fun setData(data: ByteArray)

    fun setListener(listener: CallbackListener)

    fun execute()
}