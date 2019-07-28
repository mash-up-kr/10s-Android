package com.mashup.tenSecond.data.repository.remote

<<<<<<< HEAD:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt
=======
import com.google.firebase.auth.UserProfileChangeRequest
>>>>>>> origin/master:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt
import com.mashup.tenSecond.data.model.*
import com.mashup.tenSecond.data.repository.request.FriendRequest
import com.mashup.tenSecond.data.repository.request.JoinRequest
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import io.reactivex.Single
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
<<<<<<< HEAD:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt
    fun getProfile(): Single<Profile>


    @Multipart
    @PUT("/api/profile")
    fun changeProfile(@Body profileRequest: ProfileRequest): Single<Message>
=======
    fun getProfile(): Single<ResultMessage>

    @Multipart
    @PUT("/api/profile")
    fun setProfile(@Body profileRequest: ProfileRequest): Single<ResultMessage>
>>>>>>> origin/master:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt

    @GET("/api/chatRoom")
    fun getChatRoomList(): Single<MutableList<ChatRoom>>

    @GET("/api/chatRoom/{id}/message/{start}")
<<<<<<< HEAD:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt
    fun getChatRoomById(@Path("id") id: String, @Path("start") start: String = "")
=======
    fun getChatRoomById(@Path("id") id :Int , @Path("start") start : Int = 0) : Single<Messages>
>>>>>>> origin/master:app/src/main/java/com/mashup/tenSecond/data/repository/remote/ApiService.kt

}