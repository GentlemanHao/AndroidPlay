package com.lbxtech.androidplay.adapter

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.utils.GlideUtil
import com.lbxtech.androidplay.widget.XImageView

class BannerAdapter(private val list: List<Banner>) : BaseAdapter<Banner>(list) {

    override fun getLayoutId() = R.layout.item_banner

    override fun bindView(holder: ViewHolder, position: Int) {
        GlideUtil.load(list[position % list.size].imagePath, holder.findView<XImageView>(R.id.iv_banner))
    }

    override fun getItemCount() = Int.MAX_VALUE
}