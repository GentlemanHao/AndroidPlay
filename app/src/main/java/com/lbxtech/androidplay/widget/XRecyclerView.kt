package com.lbxtech.androidplay.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lbxtech.androidplay.adapter.WrapRecyclerAdapter

class XRecyclerView : RecyclerView {

    private var wrapRecyclerAdapter: WrapRecyclerAdapter? = null

    private var baseAdapter: Adapter<ViewHolder>? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        adapter ?: return
        baseAdapter = adapter
        wrapRecyclerAdapter = WrapRecyclerAdapter(adapter)
        super.setAdapter(wrapRecyclerAdapter)
    }

    fun addHeaderView(view: View?) {
        view ?: return
        wrapRecyclerAdapter?.addHeaderView(view)
    }

    fun addFooterView(view: View?) {
        view ?: return
        wrapRecyclerAdapter?.addFooterView(view)
    }

    fun notifyDataSetChanged() {
        baseAdapter?.notifyDataSetChanged()
    }
}