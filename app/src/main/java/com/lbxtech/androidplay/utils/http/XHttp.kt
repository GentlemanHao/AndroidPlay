package com.lbxtech.androidplay.utils.http

object XHttp {

    fun <T, M> sendJsonRequest(requestData: T, url: String, response: Class<M>, listener: JsonDataListener<M>) {
        val httpRequest = JsonHttpRequest()
        val callbackListener = JsonCallbackListener(response, listener)
        val httpTask = HttpTask(url, requestData, httpRequest, callbackListener)
        ThreadPoolManager.addTask(httpTask)
    }

}