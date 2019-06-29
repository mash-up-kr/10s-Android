package com.mashup.tenSecond.ui.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.ItemFriendListBinding

class FriendListAdapter(val friendList: MutableList<User>) :
    RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    class FriendViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        val profile = itemView?.findViewById<ImageView>(R.id.fri_profile)
        val id = itemView?.findViewById<TextView>(R.id.fri_id)
        val state = itemView?.findViewById<TextView>(R.id.fri_state)

        fun bind(user: User) {
            item.user = user
            profile?.setImageResource(R.mipmap.ic_launcher)
            id?.text = user.id
            state?.text=user.state
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