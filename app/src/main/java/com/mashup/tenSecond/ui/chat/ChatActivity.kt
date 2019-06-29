package com.mashup.tenSecond.ui.chat

import android.os.Bundle
import com.mashup.tenSecond.R
import com.mashup.tenSecond.databinding.ActivityChatBinding
import com.namget.diaryLee.ui.base.BaseActivity

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override fun onLayoutId(): Int = R.layout.activity_chat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}