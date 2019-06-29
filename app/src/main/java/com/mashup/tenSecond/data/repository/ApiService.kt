package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.AccessToken
import com.mashup.tenSecond.data.model.User
import com.mashup.tenSecond.data.repository.request.JoinRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @GET("/friend/{}")
    fun getFriendList(@Query("query") id: String): Single<MutableList<User>>


    @GET("/api/auth")
    fun getUserAuthentication(): Single<AccessToken>

    @POST("/api/auth")
    fun joinUser(@Body joinRequest: JoinRequest): Single<AccessToken>

}