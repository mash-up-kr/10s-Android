package com.mashup.tenSecond.ui.chat

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel

class ChatRoomViewModel(val repository: Repository, val myApplication: Application) : ApplicationViewModel(myApplication){
    val TAG = "ChatRoomListViewModel"
    private val _chatRoomList : MutableLiveData<MutableList<ChatRoom>> = MutableLiveData()
    val chatRoomList : LiveData<MutableList<ChatRoom>> get() = _chatRoomList


    fun getChatRoom() {

    }





}