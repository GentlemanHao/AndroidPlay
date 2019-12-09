package com.lbxtech.androidplay.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lbxtech.androidplay.adapter.BaseAdapter

class XRecyclerView<T> : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setHeaderView(view: View?) {
        getBaseAdapter()?.setHeaderView(view)
    }

    fun setFooterView(view: View?) {
        getBaseAdapter()?.setFooterView(view)
    }

    fun enableLoadMore() {
        getBaseAdapter()?.enableLoadMore(context)
    }

    fun setData(list: List<T>, append: Boolean) {
        getBaseAdapter()?.setData(list, append)
    }

    private fun getBaseAdapter() = adapter as? BaseAdapter<T>
}