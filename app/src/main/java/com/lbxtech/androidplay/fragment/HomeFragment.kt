package com.lbxtech.androidplay.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.activity.ArticleActivity
import com.lbxtech.androidplay.adapter.BannerAdapter
import com.lbxtech.androidplay.adapter.HomeAdapter
import com.lbxtech.androidplay.bean.Article
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.bean.HomeData
import com.lbxtech.androidplay.presenter.MainPresenter
import com.lbxtech.androidplay.view.MainView
import com.lbxtech.androidplay.widget.XRecyclerView

class HomeFragment : MvpFragment<MainPresenter>(), MainView {

    @BindView(R.id.rv_home)
    private var rvHome: XRecyclerView<Article>? = null

    private val bannerAdapter by lazy { BannerAdapter() }

    private var page = 0

    override fun getLayoutId() = R.layout.fragment_home

    override fun onBindView() {
        val context = context ?: return

        rvHome?.run {
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter().apply {
                setOnItemClickListener { _, data ->
                    val intent = Intent(activity, ArticleActivity::class.java)
                    intent.putExtra("link", data?.link)
                    activity?.startActivity(intent)
                }
            }
        }

        mPresenter = MainPresenter().apply {
            mView = this@HomeFragment
            //getBanner()
            getHomeData(page)
        }
    }

    override fun onBannerResult(bannerList: List<Banner>) {
        bannerAdapter.setData(bannerList)
    }

    override fun onHomeDataResult(data: HomeData) {
        rvHome?.setData(data.datas)
    }

}