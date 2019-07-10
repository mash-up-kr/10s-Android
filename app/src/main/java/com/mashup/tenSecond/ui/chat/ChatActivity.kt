package com.mashup.tenSecond.ui.chat

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatContent
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.ActivityChatBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatAdapter
import com.mashup.tenSecond.ui.chat.adapter.ChatListAdapter
import com.namget.diaryLee.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_chat
    val chatList: MutableList<User> = arrayListOf()
    val chatAdapter: ChatAdapter = ChatAdapter(chatList)


    val diffCallback = object : DiffUtil.ItemCallback<ChatContent>() {
        override fun areItemsTheSame(oldItem: ChatContent, newItem: ChatContent): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ChatContent, newItem: ChatContent): Boolean =
            oldItem.name == newItem.name
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setRecyclerView() {
        chatRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.itemAnimator = DefaultItemAnimator()
            this.addItemDecoration(SimpleDividerItemDecoration(context))
            this.adapter = chatAdapter
        }
    }

}