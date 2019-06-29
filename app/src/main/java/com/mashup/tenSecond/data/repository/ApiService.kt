package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/friend/{}")
    fun getFriendList(@Query("query") id: String): Single<MutableList<User>>

}