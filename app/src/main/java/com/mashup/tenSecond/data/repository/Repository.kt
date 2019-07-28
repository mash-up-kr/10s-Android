package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
<<<<<<< HEAD
import com.mashup.tenSecond.data.repository.request.ProfileRequest
=======
>>>>>>> origin/master
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

<<<<<<< HEAD
    fun getProfile(): Single<Profile>
    fun changeProfile(profileRequest: ProfileRequest): Single<Message>
=======
    fun getProfile(): Single<ResultMessage>
>>>>>>> origin/master
    fun getChatRoomList(): Single<MutableList<ChatRoom>>
    fun getChatRoomById(id: Int, start : Int = 0) : Single<Messages>
}