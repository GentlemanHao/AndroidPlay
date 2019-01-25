package com.lbxtech.androidplay.comm

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.security.MessageDigest

fun <T> Observable<T>.execute(observer: BaseObserver<T>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}

fun String.Md5Encoder(): String {
    val sb = StringBuffer()
    MessageDigest.getInstance("MD5").digest(toByteArray()).forEach {
        var hexString = Integer.toHexString(it.toInt() and 0xFF)
        if (hexString.length < 2) hexString = "0$hexString"
        sb.append(hexString)
    }
    return sb.toString()
}