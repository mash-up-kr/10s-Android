package com.mashup.tenSecond.data.repository.remote

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody


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

    override fun changeProfile(profileRequest: Map<String,RequestBody>,image : MultipartBody.Part): Single<ResultMessage> = apiService.changeProfile(profileRequest,image)

    override fun getProfile(): Single<Profile> = apiService.getProfile()
    override fun getChatRoomList(): Single<MutableList<ChatRoom>> = apiService.getChatRoomList()
    override fun getChatRoomById(id: Int, start: Int): Single<Messages> = apiService.getChatRoomById(id,start)
}
