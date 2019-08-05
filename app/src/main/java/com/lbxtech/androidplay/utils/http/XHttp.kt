package com.lbxtech.androidplay.utils.http

object XHttp {

    fun <T, M> sendJsonRequest(requestData: T, url: String, response: Class<M>, callback: Callback<M>) {
        val httpTask = HttpTask(url, requestData, response, HttpRequest(), callback)
        ThreadPoolManager.addTask(httpTask)
    }
}