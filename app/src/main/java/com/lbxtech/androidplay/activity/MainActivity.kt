package com.lbxtech.androidplay.activity

import android.os.Bundle
import android.util.Log
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.presenter.MainPresenter
import com.lbxtech.androidplay.view.MainView

class MainActivity : MvpActivity<MainPresenter>(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter = MainPresenter()
        mPresenter.mView = this
        mPresenter.getBanner()
    }

    override fun onBannerResult(bannerList: List<Banner>) {
        bannerList.forEach {
            Log.d("--wh--", it.title)
        }
    }
}
