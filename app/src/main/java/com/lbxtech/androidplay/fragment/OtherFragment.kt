package com.lbxtech.androidplay.fragment

import android.view.View
import android.widget.TextView
import java.util.*

class OtherFragment : BaseFragment() {

    override fun initCustomLayout(): View? {
        return TextView(activity).apply {

        }
    }

    override fun onBindView() {
        (rootView as TextView).text = Random().nextInt().toString()
    }

}