package com.lbxtech.androidplay.fragment

import com.lbxtech.androidplay.presenter.BasePresenter
import com.lbxtech.androidplay.view.BaseView

abstract class MvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage() {

    }

}