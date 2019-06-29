package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.User
import io.reactivex.Single


class NetworkRemote(val apiService: ApiService) : Repository {

    override fun getFriendList(): Single<MutableList<User>> = apiService.getFriendList("1")
}
