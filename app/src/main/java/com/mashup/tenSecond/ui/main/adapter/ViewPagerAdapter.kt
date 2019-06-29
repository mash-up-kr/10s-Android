package com.namget.lottolee.ui.main.adapter


class ViewPagerAdapter(
    fm: androidx.fragment.app.FragmentManager,
    fragmentList: ArrayList<androidx.fragment.app.Fragment>
) : androidx.fragment.app.FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragments = fragmentList

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }
}