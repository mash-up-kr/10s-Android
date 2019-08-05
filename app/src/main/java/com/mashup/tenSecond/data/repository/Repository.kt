package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

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

    fun getProfile(): Single<Profile>
    fun changeProfile(profileRequest: Map<String,RequestBody>,image : MultipartBody.Part ): Single<ResultMessage>
    fun getChatRoomList(): Single<MutableList<ChatRoom>>
    fun getChatRoomById(id: Int, start : Int = 0) : Single<Messages>
}