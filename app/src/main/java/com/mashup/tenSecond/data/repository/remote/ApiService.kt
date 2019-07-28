package com.mashup.tenSecond.data.repository.remote

import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single
import retrofit2.http.*


interface ApiService {

    @GET("api/friend")
    fun getFriendList(): Single<ArrayList<Friend>>

    @POST("api/friend")
    fun addFriendList(@Body friendRequest: FriendRequest): Single<Message>

    @GET("/api/auth")
    fun getUserAuthentication(): Single<AccessToken>

    @POST("/api/auth")
    fun joinUser(@Body joinRequest: JoinRequest): Single<AccessToken>

    @GET("/api/profile")
    fun getProfile(): Single<Profile>


    @Multipart
    @PUT("/api/profile")
    fun changeProfile(@Body profileRequest: ProfileRequest): Single<Message>

    @GET("/api/chatRoom")
    fun getChatRoomList(): Single<MutableList<ChatRoom>>

    @GET("/api/chatRoom/{id}/message/{start}")
    fun getChatRoomById(@Path("id") id: String, @Path("start") start: String = "")

}