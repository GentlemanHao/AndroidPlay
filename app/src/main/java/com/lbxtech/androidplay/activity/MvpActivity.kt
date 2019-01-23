package com.lbxtech.androidplay.activity

import com.lbxtech.androidplay.presenter.BasePresenter
import com.lbxtech.androidplay.view.BaseView

abstract class MvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {

    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}