package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class FriendList(
    @SerializedName("friend")
    val friendList: ArrayList<Friend>)