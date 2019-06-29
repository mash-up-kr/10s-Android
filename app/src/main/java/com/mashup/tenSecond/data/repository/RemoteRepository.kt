package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.User
import io.reactivex.Single

class RemoteRepository(val networkRemote: NetworkRemote) : Repository {
    override fun getFriendList(): Single<MutableList<User>> = networkRemote.getFriendList()

}