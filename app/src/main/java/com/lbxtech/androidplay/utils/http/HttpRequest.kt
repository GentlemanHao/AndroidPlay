package com.lbxtech.androidplay.utils.http

import com.lbxtech.androidplay.utils.XGson
import java.io.*
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class HttpRequest<T> : IHttpRequest<T> {

    private var url: String? = null

    private var data: ByteArray? = null

    private var callback: Callback<T>? = null

    private var clazz: Class<T>? = null

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun setData(data: ByteArray) {
        this.data = data
    }

    override fun setCallback(callback: Callback<T>) {
        this.callback = callback
    }

    override fun setClass(clazz: Class<T>) {
        this.clazz = clazz
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
                callback?.invoke(true, XGson.parse(getContent(connection.inputStream), clazz!!))
            } else {
                callback?.invoke(false, null)
            }
        } catch (e: Exception) {
            throw RuntimeException("$url   fail catch")
        } finally {
            httpConnection?.disconnect()
        }
    }

    private fun getContent(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        val sb = StringBuilder()
        try {
            while (line != null) {
                sb.append(line)
                sb.append("\n")
                line = reader.readLine()
            }
        } catch (e: IOException) {

        } finally {
            reader.close()
            inputStream.close()
        }
        return sb.toString()
    }

}