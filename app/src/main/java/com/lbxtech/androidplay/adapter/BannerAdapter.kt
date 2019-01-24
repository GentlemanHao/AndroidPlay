package com.lbxtech.androidplay.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.utils.GlideUtil
import com.lbxtech.androidplay.widget.XImageView

class BannerAdapter(private val list: List<Banner>) : BaseAdapter<Banner>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as ViewHolder).apply {
            GlideUtil.load(list[position % list.size].imagePath, ivBanner)
        }
    }

    override fun getItemCount() = Int.MAX_VALUE

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBanner = itemView.findViewById<XImageView>(R.id.iv_banner)
    }
}