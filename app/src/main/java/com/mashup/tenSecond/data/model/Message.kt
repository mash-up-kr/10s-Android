package com.mashup.tenSecond.data.model

import android.content.BroadcastReceiver

data class Messages(val messages : List<Message>) {

    data class Message(
        val content: String,
        val date: String,
        val index: Int,
        val receiver: Int,
        val sender: Int
    )
}