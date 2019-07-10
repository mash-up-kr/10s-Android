package com.mashup.tenSecond.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.ItemFriendListBinding

class ChatListAdapter(diffUtilCallback: DiffUtil.ItemCallback<ChatRoom>) : ListAdapter<ChatRoom,ChatListAdapter.ChatListViewHolder>(diffUtilCallback) {

    class ChatListViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(chatRoom: ChatRoom) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val item: ItemFriendListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_friend_list, parent, false)
        return ChatListViewHolder(item);
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}