package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import io.reactivex.Single

interface Repository {
    fun getFriendList(): Single<FriendList>
    fun addFriendList(email: String): Single<ResultMessage>
    fun getUserAuthentication(): Single<AccessToken>
    fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken>

    fun getProfile(): Single<ResultMessage>
    fun getChatRoomList(): Single<MutableList<ChatRoom>>
    fun getChatRoomById(id: Int, start : Int = 0) : Single<Messages>
}