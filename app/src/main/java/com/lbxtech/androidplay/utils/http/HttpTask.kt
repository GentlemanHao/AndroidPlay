package com.lbxtech.androidplay.utils.http

import com.lbxtech.androidplay.utils.XGson

class HttpTask<T>(url: String, requestData: T, private val httpRequest: IHttpRequest, listener: CallbackListener) : Runnable {

    init {
        httpRequest.setUrl(url)
        httpRequest.setListener(listener)
        httpRequest.setData(XGson.toJson(requestData).toByteArray(Charsets.UTF_8))
    }

    override fun run() {
        httpRequest.execute()
    }
}