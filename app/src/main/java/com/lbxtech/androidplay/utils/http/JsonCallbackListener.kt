package com.lbxtech.androidplay.utils.http

import android.os.Handler
import android.os.Looper
import com.lbxtech.androidplay.utils.XGson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class JsonCallbackListener<T>(private val responseClass: Class<T>, private val jsonDataListener: JsonDataListener<T>) : CallbackListener {

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onSuccess(inputStream: InputStream) {
        val response = getContent(inputStream)
        handler.post {
            jsonDataListener.onSuccess(XGson.parse(response, responseClass))
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

    override fun onFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}