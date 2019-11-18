package com.lbxtech.androidplay.adapter

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.utils.GlideUtil
import com.lbxtech.androidplay.widget.XImageView

class BannerAdapter : BaseAdapter<Banner>() {

    override fun getLayoutId() = R.layout.item_banner

    override fun bindView(holder: ViewHolder, position: Int, data: Banner) {
        GlideUtil.load(data.imagePath, holder.findView<XImageView>(R.id.iv_banner))
    }
}