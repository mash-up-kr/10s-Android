package com.mashup.tenSecond

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.chat.ChatRoomListViewModel
import com.mashup.tenSecond.ui.chat.ChatRoomViewModel
import com.mashup.tenSecond.ui.friend.FriendListViewModel
import com.mashup.tenSecond.ui.setting.SettingViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val repository: Repository, val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    @SuppressWarnings("unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendListViewModel::class.java)) {
            return FriendListViewModel(repository, application) as T
        } else if (modelClass.isAssignableFrom(ChatRoomListViewModel::class.java)) {
            return ChatRoomListViewModel(repository, application) as T
        } else if (modelClass.isAssignableFrom(ChatRoomViewModel::class.java)) {
            return ChatRoomViewModel(repository, application) as T
        } else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(repository, application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}