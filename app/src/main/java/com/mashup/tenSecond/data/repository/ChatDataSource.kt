package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import io.reactivex.Single

class ChatDataSource(val chatRemoteDataSource: ChatRemoteDataSource) : Repository {

    override fun addFriendList(email: String): Single<ResultMessage> = chatRemoteDataSource.addFriendList(email)
    override fun getFriendList(): Single<FriendList> = chatRemoteDataSource.getFriendList()
    override fun getUserAuthentication(): Single<AccessToken> =
        chatRemoteDataSource.getUserAuthentication()

    override fun joinUser(
        email: String,
        nickname: String,
        auth_type: String,
        profile_image: String
    ): Single<AccessToken> {
        return chatRemoteDataSource.joinUser(email, nickname, auth_type, profile_image)
    }

    override fun getProfile(): Single<ResultMessage> = chatRemoteDataSource.getProfile()

    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = chatRemoteDataSource.getChatRoomList()
    override fun getChatRoomById(id: Int, start : Int) : Single<Messages>{
        return chatRemoteDataSource.getChatRoomById(id, start)
    }
}