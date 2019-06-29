package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.User
import io.reactivex.Single

class RemoteRepository(val networkRemote: NetworkRemote) : Repository {
    override fun getFriendList(): Single<MutableList<User>> = networkRemote.getFriendList()
    override fun getUserAuthentication(): Single<AccessToken> =
        networkRemote.getUserAuthentication()

    override fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken> {
        return networkRemote.joinUser(email, nickname, auth_type, profile_image)
    }
}