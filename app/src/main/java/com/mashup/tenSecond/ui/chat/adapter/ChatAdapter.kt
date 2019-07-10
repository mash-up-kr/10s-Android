package com.mashup.tenSecond.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.tenSecond.R
import com.mashup.tenSecond.data.model.ChatContent
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.databinding.ItemFriendListBinding
import com.mashup.tenSecond.databinding.ItemISayBinding

class ChatAdapter(val chatContents: MutableList<ChatContent>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val I_CHATTING = 1
    val FRIEND_CHATTING = 2

    class MyChatingViewHolder(val item: ItemISayBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(chatContent: ChatContent) {
//            item.user = user;
        }
    }

    class FriendChatingViewHolder(val item: ItemFriendListBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(chatContent : ChatContent) {
//            item.user = user;
        }
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

    override fun getItemCount(): Int = chatContents.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == I_CHATTING) {
            (holder as MyChatingViewHolder).bind(chatContents[position])
        }else if(getItemViewType(position) == FRIEND_CHATTING){
            (holder as FriendChatingViewHolder).bind(chatContents[position])
        }

    }
}