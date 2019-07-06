package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class Friend(val email: String,
                  val nickname: String,
                  val status: String,
                  @SerializedName("profile_image")
                  val profileImage : String)