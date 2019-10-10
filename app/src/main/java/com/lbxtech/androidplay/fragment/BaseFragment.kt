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
        rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView
    }

    override fun onStart() {
        super.onStart()

        initBindView(this, rootView)

        onBindView()
    }

    abstract fun getLayoutId(): Int

    abstract fun onBindView()
}