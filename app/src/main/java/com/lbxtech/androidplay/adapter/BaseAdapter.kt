package com.lbxtech.androidplay.adapter

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T>(private val list: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: ((Int) -> Unit)? = null

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listener != null) {
            holder.itemView.setOnClickListener { listener?.invoke(position) }
        }
    }

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        this.listener = listener
    }

}