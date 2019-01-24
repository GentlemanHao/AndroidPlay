package com.lbxtech.androidplay.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.adapter.BannerAdapter
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.presenter.MainPresenter
import com.lbxtech.androidplay.view.MainView

class HomeFragment : MvpFragment<MainPresenter>(), MainView {

    @BindView(R.id.rv_banner)
    private var bannerView: RecyclerView? = null

    override fun getLayoutId() = R.layout.fragment_home

    override fun onBindView() {

        bannerView?.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(bannerView)

        mPresenter = MainPresenter().apply {
            mView = this@HomeFragment
            getBanner()
        }
    }

    override fun onBannerResult(bannerList: List<Banner>) {
        bannerList.forEach {
            Log.d("--wh--", "${it.title}  ${it.imagePath}")
        }
        bannerView?.adapter = BannerAdapter(bannerList)
    }

}