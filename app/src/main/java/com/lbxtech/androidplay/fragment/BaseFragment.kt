package com.lbxtech.androidplay.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lbxtech.androidplay.base.initBindView

abstract class BaseFragment : Fragment() {

    protected lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = if (getLayoutId() != 0) {
            inflater.inflate(getLayoutId(), container, false)
        } else initCustomLayout()!!
        return rootView
    }

    override fun onStart() {
        super.onStart()

        initBindView(this, rootView)

        onBindView()
    }

    open fun getLayoutId() = 0

    open fun initCustomLayout(): View? {
        return null
    }

    abstract fun onBindView()
}