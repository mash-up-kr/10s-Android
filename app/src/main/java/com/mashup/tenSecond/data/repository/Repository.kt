package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.User
import io.reactivex.Single

interface Repository {
    fun getFriendList(): Single<MutableList<User>>
    fun getUserAuthentication(): Single<AccessToken>
    fun joinUser( email: String,  nickname: String,  auth_type : String,  profile_image : String): Single<AccessToken>
}