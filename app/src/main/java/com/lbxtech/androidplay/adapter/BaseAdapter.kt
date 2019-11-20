package com.lbxtech.androidplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER = -1
    private val CONTENT = 0
    private val FOOTER = 1
    private var headerView: View? = null
    private var footerView: View? = null

    private val data by lazy { ArrayList<T>() }

    private var itemClickListener: ((position: Int, data: T?) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && headerViewSize() > 0) {
            return HEADER
        }

        if (data.size > 0 && position < data.size + headerViewSize()) {
            return CONTENT
        }

        return FOOTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderHolder(headerView!!)

            CONTENT -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
                val holder = ViewHolder(itemView)
                if (itemClickListener != null) {
                    holder.itemView.setOnClickListener {
                        val realPosition = holder.layoutPosition - headerViewSize()
                        itemClickListener!!.invoke(realPosition, data[realPosition])
                    }
                }
                return holder
            }

            else -> FooterHolder(footerView!!)
        }
    }

    override fun getItemCount() = data.size + headerViewSize() + footerViewSize()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderHolder -> {
            }
            is FooterHolder -> {
            }
            is ViewHolder -> {
                val realPosition = position - headerViewSize()
                bindView(holder, realPosition, data[realPosition])
            }
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun bindView(holder: ViewHolder, position: Int, data: T)

    fun setData(list: List<T>, append: Boolean = true) {
        if (!append) data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (position: Int, data: T?) -> Unit) {
        this.itemClickListener = listener
    }

    fun setHeaderView(view: View?) {
        headerView = view
        notifyDataSetChanged()
    }

    fun setFooterView(view: View?) {
        footerView = view
        notifyDataSetChanged()
    }

    private fun headerViewSize() = if (headerView == null) 0 else 1

    private fun footerViewSize() = if (footerView == null) 0 else 1

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun <T : View> findView(id: Int): T {
            return itemView.findViewById(id)
        }
    }

    class HeaderHolder(itemView: View) : ViewHolder(itemView)

    class FooterHolder(itemView: View) : ViewHolder(itemView)
}