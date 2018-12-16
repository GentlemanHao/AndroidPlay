package com.lbxtech.androidplay.comm

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(result: T) {
    }

    override fun onError(e: Throwable) {
    }

}