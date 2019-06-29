package com.mashup.tenSecond.ui.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.ItemFriendListBinding

class FriendListAdapter(val friendList: MutableList<User>) :
    RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    class FriendViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(user: User) {
            item.user = user
        }
    }

    fun setItem(item: MutableList<User>) {
        friendList.clear()
        friendList.addAll(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val item: ItemFriendListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_friend_list, parent, false)
        return FriendViewHolder(item)
    }

    override fun getItemCount(): Int = friendList.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friendList.get(position))
    }
}