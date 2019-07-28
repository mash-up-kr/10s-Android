package com.mashup.tenSecond.ui.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.FriendList
import com.mashup.tenSecond.databinding.ItemFriendListBinding

class FriendListAdapter(diffCallback: DiffUtil.ItemCallback<FriendList.Friend>) :
    ListAdapter<FriendList.Friend, FriendListAdapter.FriendViewHolder>(diffCallback) {

    class FriendViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(freind: FriendList.Friend) {
            item.friend = freind
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val item: ItemFriendListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_friend_list, parent, false)
        return FriendViewHolder(item)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}