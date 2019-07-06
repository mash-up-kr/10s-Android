package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class ChatRoom(
    @SerializedName("room_id")
    val roomId : Int,
    @SerializedName("room_name")
    val roomName : String
)