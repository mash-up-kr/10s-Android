package com.mashup.tenSecond.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.FragmentChatListBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatListAdapter
import com.namget.diaryLee.ui.base.BaseFragment

class ChatListFragment : BaseFragment<FragmentChatListBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_chat_list
    val chatList: MutableList<User> = arrayListOf()
    val chatListAdapter: ChatListAdapter = ChatListAdapter(chatList)


    companion object {
        var chatListFragment: ChatListFragment? = null

        fun getInstance(): ChatListFragment {
            if (chatListFragment == null) {
                synchronized(ChatListFragment::class) {
                    chatListFragment = ChatListFragment()
                }
            }
            return chatListFragment as ChatListFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        setRecyclerView()
        return view
    }

    private fun setRecyclerView() {
        binding.chatListRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = chatListAdapter
        }
    }

}