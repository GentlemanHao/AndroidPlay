package com.lbxtech.androidplay.utils.http

import com.lbxtech.androidplay.utils.XGson
import java.lang.Exception
import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

class HttpTask<T, M>(url: String, requestData: T, responseData: Class<M>, private val httpRequest: IHttpRequest<M>, callback: Callback<M>) : Runnable, Delayed {

    private var delayTime: Long = 0

    var retryCount = 0

    init {
        httpRequest.setUrl(url)
        httpRequest.setCallback(callback)
        httpRequest.setData(XGson.toJson(requestData).toByteArray(Charsets.UTF_8))
        httpRequest.setClass(responseData)
    }

    override fun run() {
        try {
            httpRequest.execute()
        } catch (e: Exception) {
            ThreadPoolManager.addDelayTask(this)
        }
    }

    fun setDelayTime(time: Long) {
        this.delayTime = System.currentTimeMillis() + time
    }

    override fun compareTo(other: Delayed): Int {
        return 0
    }

    override fun getDelay(unit: TimeUnit): Long {
        return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
    }
}