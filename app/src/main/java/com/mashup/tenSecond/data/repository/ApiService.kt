package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.Friend
import com.mashup.tenSecond.data.model.Message
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


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
    fun getProfile(): Single<Message>

}