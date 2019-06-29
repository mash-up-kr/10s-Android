package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.data.repository.request.JoinRequest
import io.reactivex.Single


class NetworkRemote(val apiService: ApiService) : Repository {

    override fun getFriendList(): Single<MutableList<User>> = apiService.getFriendList("1")
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
}
