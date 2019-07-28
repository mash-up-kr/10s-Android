package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.remote.ApiService
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import io.reactivex.Single


class ChatRemoteDataSource(val apiService: ApiService) : Repository {

    override fun getFriendList(): Single<FriendList> = apiService.getFriendList()
    override fun addFriendList(email: String): Single<ResultMessage> =
        apiService.addFriendList(FriendRequest(email))

    override fun getUserAuthentication(): Single<AccessToken> {
        return apiService.getUserAuthentication()
    }

    override fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken> {
        return apiService.joinUser(JoinRequest(email, nickname, auth_type, profile_image))
    }

    override fun getProfile(): Single<ResultMessage> = apiService.getProfile()
    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = apiService.getChatRoomList()
    override fun getChatRoomById(id: Int, start: Int): Single<Messages> = apiService.getChatRoomById(id,start)
}
