package com.mashup.tenSecond.ui.friend

import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.FragmentFriendlistBinding
import com.namget.diaryLee.ui.base.BaseFragment

class FriendListFragment : BaseFragment<FragmentFriendlistBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_friendlist

    companion object {
        var friendListFragment: FriendListFragment? = null

        fun getInstance(): FriendListFragment {
            if (friendListFragment == null) {
                synchronized(FriendListFragment::class) {
                    friendListFragment = FriendListFragment()
                }
            }
            return friendListFragment as FriendListFragment
        }
    }

}