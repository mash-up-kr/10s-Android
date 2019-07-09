package com.mashup.tenSecond.ui.setting

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mashup.tenSecond.data.repository.Repository

class SettingViewModelFactory(val repository: Repository, val myApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(repository, myApplication) as T
    }
}