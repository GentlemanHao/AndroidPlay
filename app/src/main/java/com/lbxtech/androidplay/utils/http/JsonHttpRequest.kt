package com.lbxtech.androidplay.utils.http

import java.io.BufferedOutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class JsonHttpRequest : IHttpRequest {

    private var url: String? = null

    private var data: ByteArray? = null

    private var listener: CallbackListener? = null

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun setData(data: ByteArray) {
        this.data = data
    }

    override fun setListener(listener: CallbackListener) {
        this.listener = listener
    }

    private var httpConnection: HttpURLConnection? = null

    override fun execute() {
        try {
            httpConnection = (URL(url).openConnection() as HttpURLConnection).apply {
                connectTimeout = 6000
                useCaches = false
                instanceFollowRedirects = true
                readTimeout = 3000
                doInput = true
                doOutput = true
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json;charset=UTF-8")
            }
            val connection = httpConnection ?: return
            val outputStream = connection.outputStream
            val bufferedOutputStream = BufferedOutputStream(outputStream)
            bufferedOutputStream.write(data)
            bufferedOutputStream.flush()
            outputStream.close()
            bufferedOutputStream.close()
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                listener?.onSuccess(connection.inputStream)
            } else {

            }
        } catch (e: Exception) {

        } finally {
            httpConnection?.disconnect()
        }
    }

}