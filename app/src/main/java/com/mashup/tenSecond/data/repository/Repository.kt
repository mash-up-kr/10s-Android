package com.mashup.tenSecond.data.repository

import com.mashup.tenSecond.data.model.User
import io.reactivex.Single

interface Repository {
    fun getFriendList() : Single<MutableList<User>>
}