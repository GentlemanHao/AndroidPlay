package com.lbxtech.androidplay.presenter

import com.lbxtech.androidplay.view.BaseView

open class BasePresenter<T : BaseView> {
    lateinit var mView: T
}