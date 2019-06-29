package com.mashup.tenSecond.data.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("auth_token")
    val accessToken : String
)