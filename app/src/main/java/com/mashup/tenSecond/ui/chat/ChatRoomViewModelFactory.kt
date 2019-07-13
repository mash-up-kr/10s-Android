package com.mashup.tenSecond.ui.chat

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mashup.tenSecond.data.repository.Repository

class ChatRoomViewModelFactory(val repository: Repository, val myApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatRoomViewModel(repository, myApplication) as T
    }
}