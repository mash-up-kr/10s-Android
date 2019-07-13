package com.mashup.tenSecond.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.FragmentChatListBinding
import com.mashup.tenSecond.ui.base.SimpleDividerItemDecoration
import com.mashup.tenSecond.ui.chat.adapter.ChatListAdapter
import com.mashup.tenSecond.util.Constant
import com.namget.diaryLee.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class ChatListFragment : BaseFragment<FragmentChatListBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_chat_list
    val chatList: MutableList<User> = arrayListOf()

    val chatRoomListViewModelFactory: ChatRoomListViewModelFactory by inject()
    lateinit var chatRoomListViewModel: ChatRoomListViewModel

    interface ItemClickCallback {
        fun click(id: Int)
    }

    val itemClickCallback = object : ItemClickCallback {
        override fun click(id: Int) {
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(Constant.CHAT_ROOM_ID, id)
            startActivity(intent)
        }
    }


    val diffCallback = object : DiffUtil.ItemCallback<ChatRoom>() {
        override fun areItemsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean =
            oldItem.roomId == newItem.roomId

        override fun areContentsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean =
            oldItem.roomId == newItem.roomId
    }

    val chatListAdapter: ChatListAdapter = ChatListAdapter(diffCallback, itemClickCallback)


    companion object {
        lateinit var chatListFragment: ChatListFragment

        fun newInstance(): ChatListFragment {
            synchronized(ChatListFragment::class) {
                chatListFragment = ChatListFragment()
                val args = Bundle()
                chatListFragment.arguments = args
                return chatListFragment
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
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

    private fun initViewModel() {
        chatRoomListViewModel = ViewModelProviders.of(this, chatRoomListViewModelFactory)
            .get(ChatRoomListViewModel::class.java)
        chatRoomListViewModel.chatRoomList.observe(this, Observer {
            chatListAdapter.submitList(it)
        })

        chatRoomListViewModel.getChatRoomList()
    }


}