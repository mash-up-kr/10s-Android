package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.Message
import io.reactivex.Single

class RemoteRepository(val networkRemote: NetworkRemote) : Repository {

    override fun addFriendList(email: String): Single<Message> = networkRemote.addFriendList(email)
    override fun getFriendList(): Single<ArrayList<Friend>> = networkRemote.getFriendList()
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

    override fun getProfile(): Single<Message> = networkRemote.getProfile()

    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = networkRemote.getChatRoomList()
    override fun getChatRoomById(id: String, start: String) {
        networkRemote.getChatRoomById(id, start)
    }
}