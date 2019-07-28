package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class Profile(val nickname : String,
                   @SerializedName("status_message")
                   val status : String,
                   @SerializedName("profile_image")
                   val profileImage : String)