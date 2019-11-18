package com.lbxtech.androidplay.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lbxtech.androidplay.fragment.BaseFragment

class HomePagerAdapter(activity: FragmentActivity, val list: List<BaseFragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}