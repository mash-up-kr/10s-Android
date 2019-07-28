package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single

class ApiDataSource(val remoteDataSource: RemoteDataSource) : Repository {

    override fun addFriendList(email: String): Single<Message> = remoteDataSource.addFriendList(email)
    override fun getFriendList(): Single<ArrayList<Friend>> = remoteDataSource.getFriendList()
    override fun getUserAuthentication(): Single<AccessToken> =
        remoteDataSource.getUserAuthentication()

    override fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken> {
        return remoteDataSource.joinUser(email, nickname, auth_type, profile_image)
    }

    override fun getProfile(): Single<Profile> = remoteDataSource.getProfile()
    override fun changeProfile(profileRequest: ProfileRequest): Single<Message> =
        remoteDataSource.changeProfile(profileRequest)

    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = remoteDataSource.getChatRoomList()
    override fun getChatRoomById(id: String, start: String) {
        remoteDataSource.getChatRoomById(id, start)
    }
}