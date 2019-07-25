package com.mashup.tenSecond.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.Messages
import com.mashup.tenSecond.databinding.ItemFriendListBinding
import com.mashup.tenSecond.databinding.ItemISayBinding

class ChatAdapter(val messages: MutableList<Messages.Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val I_CHATTING = 1
    val FRIEND_CHATTING = 2

    class MyChatingViewHolder(val item: ItemISayBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(message : Messages.Message) {
//            item.user = user;
        }
    }

    class FriendChatingViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(message : Messages.Message) {
//            item.user = user;
        }
    }

    fun addList(massages : List<Messages.Message>){
        messages.addAll(messages)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == I_CHATTING) {
            val item: ItemISayBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_i_say, parent, false)
            return MyChatingViewHolder(item)
        } else if (viewType == FRIEND_CHATTING) {
            val item: ItemFriendListBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_friend_list, parent, false)
            return FriendChatingViewHolder(item)
        } else {
            val item: ItemISayBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_i_say, parent, false)
            return MyChatingViewHolder(item)
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == I_CHATTING) {
            (holder as MyChatingViewHolder).bind(messages[position])
        }else if(getItemViewType(position) == FRIEND_CHATTING){
            (holder as FriendChatingViewHolder).bind(messages[position])
        }

    }
}