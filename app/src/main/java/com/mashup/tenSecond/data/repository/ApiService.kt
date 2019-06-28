package com.mashup.tenSecond.data.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/v1/search/movie.json")
    fun getMovieList(@Query("query") query: String, @Query("display") display: Int? = 20, @Query("start") start: Int = 1): Single<String>

}