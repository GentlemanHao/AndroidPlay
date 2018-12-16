package com.lbxtech.androidplay.presenter

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.bean.Result
import com.lbxtech.androidplay.comm.BaseObserver
import com.lbxtech.androidplay.comm.execute
import com.lbxtech.androidplay.utils.HttpUtil
import com.lbxtech.androidplay.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    fun getBanner() {
        HttpUtil.api.getBanner().execute(object : BaseObserver<Result<List<Banner>>>() {
            override fun onNext(result: Result<List<Banner>>) {
                mView.onBannerResult(result.data)
            }
        })
    }
}