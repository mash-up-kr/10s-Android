package com.mashup.tenSecond.ui.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.databinding.FragmentFriendlistBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.friend.adapter.FriendListAdapter
import com.namget.diaryLee.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class FriendListFragment : BaseFragment<FragmentFriendlistBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_friendlist
    var friendList: MutableList<User> = arrayListOf(
        User("aaa","aaaa","mash0","mashup0","mashup_and0"),
        User("bbb","bbbb","mash1","mashup1","mashup_and1"),
        User("ccc","cccc","mash2","mashup2","mashup_and2")
    )
    val friendListAdapter: FriendListAdapter = FriendListAdapter(friendList)
    val repository: Repository by inject()


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setRecyclerView()
        getFriendList()
        //dummy()

        return view
    }

    private fun setRecyclerView() {
        binding.friendListRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = friendListAdapter
        }
    }

    fun dummy(){
        friendList.add(User("aaa","aaaa","mash","mashup","mashup_and"))
        friendList.add(User("bbb","bbbb","mash2","mashup2","mashup_and2"))
        friendList.add(User("ccc","cccc","mash3","mashup3","mashup_and3"))
        friendListAdapter.setItem(friendList)
    }

    private fun getFriendList() {
//        repository.getFriendList().subscribeOn(Schedulers.newThread()).subscribe(
//            {
//                friendListAdapter.setItem(it)
//            },
//            {
//
//            })
    }

    private fun addFriendList() {

    }

    private fun deleteFriendList() {

    }

}