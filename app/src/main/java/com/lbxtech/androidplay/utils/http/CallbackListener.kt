package com.lbxtech.androidplay.utils.http

import java.io.InputStream

interface CallbackListener {

    fun onSuccess(inputStream: InputStream)

    fun onFail()
}