package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single

interface Repository {
    fun getFriendList(): Single<ArrayList<Friend>>
    fun addFriendList(email: String): Single<Message>
    fun getUserAuthentication(): Single<AccessToken>
    fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken>

    fun getProfile(): Single<Profile>
    fun changeProfile(profileRequest: ProfileRequest): Single<Message>
    fun getChatRoomList(): Single<MutableList<ChatRoom>>
    fun getChatRoomById(id: String, start: String)
}