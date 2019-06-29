package com.mashup.tenSecond.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.ActivityMainBinding
import com.mashup.tenSecond.ui.chat.ChatFragment
import com.mashup.tenSecond.ui.friend.FriendListFragment
import com.mashup.tenSecond.ui.setting.SettingFragment
import com.namget.diaryLee.ui.base.BaseActivity
import com.namget.lottolee.ui.main.adapter.ViewPagerAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var fragmentList: ArrayList<Fragment>
    override fun onLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
        setupBottomNavagion()
    }



    fun setupViewPager() {
        fragmentList = ArrayList<Fragment>().apply {
            add(FriendListFragment.getInstance())
            add(ChatFragment.getInstance())
            add(SettingFragment.getInstance())
        }

        binding.viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
            currentItem = 0
            offscreenPageLimit = 3

            addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.navigation.selectedItemId = R.id.navigationFriendList
                        }
                        1 -> {
                            binding.navigation.selectedItemId = R.id.navigationWrite
                        }
                        2 -> {
                            binding.navigation.selectedItemId = R.id.navigationSetting
                        }
                    }
                }
            })
        }
    }

    fun setupBottomNavagion() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationFriendList -> {
                    binding.viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationWrite -> {
                    binding.viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigationSetting -> {
                    binding.viewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }?.let { binding.navigation.setOnNavigationItemSelectedListener(it) }
    }
}
