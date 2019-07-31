package com.lbxtech.androidplay.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<T>(private val list: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        val holder = ViewHolder(itemView)
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener {
                itemClickListener!!.invoke(holder.layoutPosition)
            }
        }
        return holder
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindView(holder as ViewHolder, position)
    }

    abstract fun getLayoutId(): Int

    abstract fun bindView(holder: ViewHolder, position: Int)

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        this.itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun <T : View> findView(id: Int): T {
            return itemView.findViewById(id)
        }
    }
}