package com.lbxtech.androidplay.view

import com.lbxtech.androidplay.bean.Banner

interface MainView : BaseView {
    fun onBannerResult(bannerList: List<Banner>)
}