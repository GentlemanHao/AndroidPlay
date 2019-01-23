package com.lbxtech.androidplay.fragment

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.R

class HomeFragment : BaseFragment() {

    @BindView(R.id.rv_banner)
    private var bannerView: RecyclerView? = null

    override fun getLayoutId() = R.layout.fragment_home

    override fun bindView() {
        Log.d("--wh--", "bannerView:$bannerView")
    }

}