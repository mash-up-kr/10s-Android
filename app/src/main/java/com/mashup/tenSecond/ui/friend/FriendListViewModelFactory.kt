package com.mashup.tenSecond.ui.friend

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mashup.tenSecond.data.repository.Repository

class FriendListViewModelFactory(val repository: Repository, val myApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendListViewModel(repository, myApplication) as T
    }
}