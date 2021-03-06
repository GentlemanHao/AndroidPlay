package com.lbxtech.androidplay.activity

import com.lbxtech.androidplay.presenter.BasePresenter
import com.lbxtech.androidplay.view.BaseView

abstract class MvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage() {

    }

}