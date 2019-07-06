package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class Profile(val nickname : String,
                   val status : String,
                   @SerializedName("profile_image")
                   val profileImage : String)