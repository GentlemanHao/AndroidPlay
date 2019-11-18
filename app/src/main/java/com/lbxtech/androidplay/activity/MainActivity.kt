package com.lbxtech.androidplay.activity

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.lbxtech.androidplay.R
import com.lbxtech.androidplay.adapter.HomePagerAdapter
import com.lbxtech.androidplay.base.BindView
import com.lbxtech.androidplay.fragment.HomeFragment
import com.lbxtech.androidplay.fragment.OtherFragment

class MainActivity : BaseActivity() {

    @BindView(R.id.fragment_content)
    private var viewPager: ViewPager2? = null

    @BindView(R.id.home_navigation)
    private var bottomNavigationView: BottomNavigationView? = null

    override fun getLayoutId() = R.layout.activity_main

    override fun onBindView() {
        val list = arrayListOf(HomeFragment(), OtherFragment(), OtherFragment(), OtherFragment())
        viewPager?.run {
            adapter = HomePagerAdapter(this@MainActivity, list)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    bottomNavigationView?.run {
                        selectedItemId = menu.getItem(position).itemId
                    }
                }
            })
        }

        bottomNavigationView?.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener {
                viewPager?.currentItem = when (it.itemId) {
                    R.id.main_article -> 0
                    R.id.main_article1 -> 1
                    R.id.main_article2 -> 2
                    R.id.main_article3 -> 3
                    else -> 0
                }
                true
            }
        }
    }
}
