package com.mashup.tenSecond.ui.chat

import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.FragmentChatBinding
import com.namget.diaryLee.ui.base.BaseFragment

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override fun onLayoutId(): Int = R.layout.fragment_chat

    companion object {
        var chatFragment: ChatFragment? = null

        fun getInstance(): ChatFragment {
            if (chatFragment == null) {
                synchronized(ChatFragment::class) {
                    chatFragment = ChatFragment()
                }
            }
            return chatFragment as ChatFragment
        }
    }

}