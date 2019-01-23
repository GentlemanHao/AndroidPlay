package com.lbxtech.androidplay.activity

import android.util.Log
import android.widget.TextView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.presenter.MainPresenter
import com.lbxtech.androidplay.view.MainView

class MainActivity : MvpActivity<MainPresenter>(), MainView {

    @BindView(R.id.tv_main)
    private var tvMain: TextView? = null

    private var time = 0

    override fun getLayoutId() = R.layout.activity_main

    override fun bindView() {
        mPresenter = MainPresenter()
        mPresenter.mView = this
        mPresenter.getBanner()

        tvMain?.setOnClickListener {
            mPresenter.getBanner()
        }
    }

    override fun onBannerResult(bannerList: List<Banner>) {
        Log.d("--wh--", "$tvMain")
        tvMain?.text = "onBannerResult ${time++}"
        bannerList.forEach {
            Log.d("--wh--", it.title)
        }
    }
}
