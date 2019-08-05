package com.mashup.tenSecond.data.repository.remote


import com.google.firebase.auth.UserProfileChangeRequest
import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {

    @GET("api/friend")
    fun getFriendList(): Single<FriendList>

    @POST("api/friend")
    fun addFriendList(@Body friendRequest: FriendRequest): Single<ResultMessage>

    @GET("/api/auth")
    fun getUserAuthentication(): Single<AccessToken>

    @POST("/api/auth")
    fun joinUser(@Body joinRequest: JoinRequest): Single<AccessToken>

    @GET("/api/profile")
    fun getProfile(): Single<Profile>

    @Multipart
    @PUT("/api/profile")
    fun changeProfile(@PartMap profileRequest: Map<String, @JvmSuppressWildcards RequestBody>, @Part image : MultipartBody.Part ): Single<ResultMessage>



    @GET("/api/chatRoom")
    fun getChatRoomList(): Single<MutableList<ChatRoom>>

    @GET("/api/chatRoom/{id}/message/{start}")
    fun getChatRoomById(@Path("id") id :Int , @Path("start") start : Int = 0) : Single<Messages>

}