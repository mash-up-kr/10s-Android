package com.mashup.tenSecond.ui.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.databinding.ItemFriendListBinding

class FriendListAdapter(diffCallback: DiffUtil.ItemCallback<Friend>) :
    ListAdapter<Friend, FriendListAdapter.FriendViewHolder>(diffCallback) {

    class FriendViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        val profile = itemView?.findViewById<ImageView>(R.id.fri_profile)
        val id = itemView?.findViewById<TextView>(R.id.friendId)
        val state = itemView?.findViewById<TextView>(R.id.friendState)

        fun bind(freind: Friend) {
//            item.user = user
//            profile?.setImageResource(R.mipmap.ic_launcher)
//            id?.text = user.id
//            state?.text = user.state
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