package com.lbxtech.androidplay.activity

import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun onBindView() {
        //registerNetworkReceiver()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_content, HomeFragment()).commit()
    }
}
