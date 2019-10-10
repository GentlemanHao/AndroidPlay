package com.lbxtech.androidplay.fragment

import androidx.viewpager2.widget.ViewPager2
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.adapter.BannerAdapter
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.presenter.MainPresenter
import com.lbxtech.androidplay.view.MainView

class HomeFragment : MvpFragment<MainPresenter>(), MainView {

    @BindView(R.id.vp_banner)
    private var viewPager: ViewPager2? = null

    override fun getLayoutId() = R.layout.fragment_home

    override fun onBindView() {

        viewPager?.adapter = BannerAdapter()

        mPresenter = MainPresenter().apply {
            mView = this@HomeFragment
            getBanner()
        }
    }

    override fun onBannerResult(bannerList: List<Banner>) {
        (viewPager?.adapter as? BannerAdapter)?.setData(bannerList)
    }

}