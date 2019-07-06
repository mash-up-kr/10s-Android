package com.mashup.tenSecond.data.model

import android.content.Context
import com.mashup.tenSecond.util.Constant
import com.mashup.tenSecond.util.getStringPreference
import com.mashup.tenSecond.util.setStringPreference

object UserInstance {
    private var userToken: String = ""
    private var email: String = ""

    fun saveUserToken(context: Context, token: String) {
        userToken = token
        context.setStringPreference(Constant.USER_TOKEN, token)
    }

    fun loadUserToken(context: Context): String {
        userToken = context.getStringPreference(Constant.USER_TOKEN)
        return userToken
    }

    fun saveUserProfile(context: Context, name: String, email: String, status: String, imageUrl: String) {
        context.setStringPreference(Constant.USER_NAME, name)
        context.setStringPreference(Constant.USER_EMAIL, email)
        context.setStringPreference(Constant.USER_STATUS, status)
        context.setStringPreference(Constant.USER_IMAGE_URL, imageUrl)
    }

    fun loadUserProfile(context: Context): User {
        email = context.getStringPreference(Constant.USER_EMAIL)
        return User(
            userName = context.getStringPreference(Constant.USER_NAME),
            userEmail = context.getStringPreference(Constant.USER_EMAIL),
            status = context.getStringPreference(Constant.USER_STATUS),
            PhotoUrl = context.getStringPreference(Constant.USER_IMAGE_URL)
        )
    }

    fun getUserToken(): String = userToken
    fun getEmail(): String = email
}