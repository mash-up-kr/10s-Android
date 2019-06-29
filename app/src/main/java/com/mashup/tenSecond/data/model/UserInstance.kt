package com.mashup.tenSecond.data.model

import android.content.Context
import com.mashup.tenSecond.util.Constant
import com.mashup.tenSecond.util.getStringPreference
import com.mashup.tenSecond.util.setStringPreference

object UserInstance {
    private var userToken: String = ""
    private var email: String = ""

    fun saveUserName(context: Context, name: String) = context.setStringPreference(context, Constant.USER_NAME, name)
    fun loadUserName(context: Context): String = context.getStringPreference(context, Constant.USER_NAME)
    fun saveUserEmail(context: Context, email: String) {
        this.email = email
        context.setStringPreference(context, Constant.USER_EMAIL, email)

    }

    fun loadUserEmail(context: Context): String {
        this.email = context.getStringPreference(context, Constant.USER_EMAIL)
        return email
    }

    fun saveUserToken(context: Context, token: String) {
        userToken = token
        context.setStringPreference(context, Constant.USER_TOKEN, token)
    }

    fun loadUserToken(context: Context): String {
        userToken = context.getStringPreference(context, Constant.USER_TOKEN)
        return userToken
    }

    fun getUserToken(): String = userToken
    fun getEmail(): String = email
}