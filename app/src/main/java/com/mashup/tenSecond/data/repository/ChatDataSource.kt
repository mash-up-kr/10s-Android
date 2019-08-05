package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.remote.ChatRemoteDataSource
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
    ): Single<AccessToken> = chatRemoteDataSource.joinUser(email, nickname, auth_type, profile_image)

    override fun changeProfile(profileRequest: Map<String,RequestBody>, image: MultipartBody.Part): Single<ResultMessage> =
        chatRemoteDataSource.changeProfile(profileRequest, image)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getProfile(): Single<Profile> = chatRemoteDataSource.getProfile()

    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = chatRemoteDataSource.getChatRoomList()
    override fun getChatRoomById(id: Int, start: Int): Single<Messages> =
        chatRemoteDataSource.getChatRoomById(id, start)

}