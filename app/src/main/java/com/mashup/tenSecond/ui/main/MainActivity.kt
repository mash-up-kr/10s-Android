package com.mashup.tenSecond.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.UserInstance
import com.mashup.tenSecond.databinding.ActivityMainBinding
import com.mashup.tenSecond.ui.chat.ChatListFragment
import com.mashup.tenSecond.ui.friend.FriendListFragment
import com.namget.diaryLee.ui.base.BaseActivity
import com.namget.lottolee.ui.main.adapter.ViewPagerAdapter
import android.util.TypedValue
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var fragmentList: ArrayList<Fragment>
    override fun onLayoutId(): Int = com.mashup.tenSecond.R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewPager()
        setupBottomNavagion()
        setupBottomIconSize()
    }

    fun setupViewPager() {
        fragmentList = ArrayList<Fragment>().apply {
            add(FriendListFragment.newInstance())
            add(ChatListFragment.newInstance())
        }

        binding.viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
            currentItem = 0
            offscreenPageLimit = 2

            addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.navigation.selectedItemId = com.mashup.tenSecond.R.id.navigationFriendList
                        }
                        1 -> {
                            binding.navigation.selectedItemId = com.mashup.tenSecond.R.id.navigationWrite
                        }
                    }
                }
            })
        }
    }

    fun setupBottomIconSize(){
        val bottomNavigationView = findViewById<BottomNavigationView>(com.mashup.tenSecond.R.id.navigation)
        val menuView = (bottomNavigationView).getChildAt(0)
        for (i in 0 until (menuView as BottomNavigationMenuView).childCount) {
            val iconView = menuView.getChildAt(i).findViewById<View>(com.mashup.tenSecond.R.id.navigationFriendList)
            val layoutParams = iconView.getLayoutParams()
            val displayMetrics = resources.displayMetrics
            // set your height here
            layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, displayMetrics).toInt()
            // set your width here
            layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32f, displayMetrics).toInt()
            iconView.setLayoutParams(layoutParams)
        }
    }

    fun setupBottomNavagion() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                com.mashup.tenSecond.R.id.navigationFriendList -> {
                    binding.viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                com.mashup.tenSecond.R.id.navigationWrite -> {
                    binding.viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }?.let { navigation.setOnNavigationItemSelectedListener(it) }
    }
}
