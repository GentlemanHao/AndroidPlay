package com.lbxtech.androidplay.view

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.bean.HomeData

interface MainView : BaseView {
    fun onBannerResult(bannerList: List<Banner>)
    fun onHomeDataResult(data: HomeData)
}