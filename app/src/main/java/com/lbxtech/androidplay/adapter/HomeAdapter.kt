package com.lbxtech.androidplay.adapter

import android.widget.TextView
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.bean.Article

class HomeAdapter : BaseAdapter<Article>() {
    override fun getLayoutId() = R.layout.item_home

    override fun bindView(holder: ViewHolder, position: Int, data: Article) {
        holder.findView<TextView>(R.id.item_text).text = data.title
    }

}