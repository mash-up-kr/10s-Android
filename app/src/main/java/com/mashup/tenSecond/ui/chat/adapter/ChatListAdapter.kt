package com.mashup.tenSecond.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.databinding.ItemChatListBinding
import com.mashup.tenSecond.ui.chat.ChatListFragment

class ChatListAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<ChatRoom>,
    val callback: ChatListFragment.ItemClickCallback
) : ListAdapter<ChatRoom, ChatListAdapter.ChatListViewHolder>(diffUtilCallback) {

    inner class ChatListViewHolder(val item: ItemChatListBinding) :
        RecyclerView.ViewHolder(item.root) {
        init {
            item.chatRoomLayout.setOnClickListener {
                callback.click(getItem(adapterPosition).roomId)
            }
        }
        fun bind(chatRoom: ChatRoom) {
            item.chatRoom = chatRoom
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val item: ItemChatListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_chat_list,
                parent,
                false
            )
        return ChatListViewHolder(item)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}