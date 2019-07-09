package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.Message
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import io.reactivex.Single


class NetworkRemote(val apiService: ApiService) : Repository {

    override fun getFriendList(): Single<ArrayList<Friend>> = apiService.getFriendList()
    override fun addFriendList(email: String): Single<Message> = apiService.addFriendList(FriendRequest(email))
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

    override fun getProfile(): Single<Message> = apiService.getProfile()
}
