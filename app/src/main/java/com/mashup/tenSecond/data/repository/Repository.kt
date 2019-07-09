package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.Message
import io.reactivex.Single

interface Repository {
    fun getFriendList(): Single<ArrayList<Friend>>
    fun addFriendList(email: String): Single<Message>
    fun getUserAuthentication(): Single<AccessToken>
    fun joinUser(email: String, nickname: String, auth_type: String, profile_image: String): Single<AccessToken>
    fun getProfile() : Single<Message>
}