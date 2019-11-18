package com.lbxtech.androidplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lbxtech.androidplay.widget.XRecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<T>? = null

    private var itemClickListener: ((position: Int, data: T?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        val holder = ViewHolder(itemView)
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener {
                val itemCount = (parent as XRecyclerView<*>).adapter?.itemCount ?: 0
                val realPosition = if (itemCount > getItemCount()) holder.layoutPosition - (itemCount - getItemCount()) else holder.layoutPosition
                itemClickListener!!.invoke(realPosition, list?.get(realPosition))
            }
        }
        return holder
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = list ?: return
        val realPosition = if (position >= list.size) position % list.size else position
        bindView(holder as ViewHolder, realPosition, list[realPosition])
    }

    abstract fun getLayoutId(): Int

    abstract fun bindView(holder: ViewHolder, position: Int, data: T)

    fun setData(list: List<T>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (position: Int, data: T?) -> Unit) {
        this.itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun <T : View> findView(id: Int): T {
            return itemView.findViewById(id)
        }
    }
}