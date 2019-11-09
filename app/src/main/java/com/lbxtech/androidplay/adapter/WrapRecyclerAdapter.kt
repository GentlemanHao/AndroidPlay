package com.lbxtech.androidplay.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WrapRecyclerAdapter(val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headerViews by lazy { ArrayList<View>() }
    private val footerViews by lazy { ArrayList<View>() }

    fun addHeaderView(view: View) {
        if (!headerViews.contains(view)) {
            headerViews.add(view)
            notifyDataSetChanged()
        }
    }

    fun addFooterView(view: View) {
        if (!footerViews.contains(view)) {
            footerViews.add(view)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // viewType = position
        val position = viewType
        if (position < headerViews.size) {
            return getViewHolder(headerViews[position])
        }

        if (position >= headerViews.size && position < adapter.itemCount + headerViews.size) {
            return adapter.onCreateViewHolder(parent, position - headerViews.size)
        }

        return getViewHolder(footerViews[position - headerViews.size - adapter.itemCount])
    }

    private fun getViewHolder(view: View) = object : RecyclerView.ViewHolder(view) {}

    override fun getItemCount(): Int {
        return adapter.itemCount + headerViews.size + footerViews.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= headerViews.size && position < headerViews.size + adapter.itemCount) {
            adapter.onBindViewHolder(holder, position - headerViews.size)
        }
    }

}