package com.mashup.tenSecond.ui.setting

import android.app.Application
import android.content.ContentValues.TAG
import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.Profile
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SettingViewModel(val repository: Repository, val myApplication: Application) :
    ApplicationViewModel(myApplication) {


    private val _profile: MutableLiveData<Profile> = MutableLiveData()
    val profile: LiveData<Profile> = _profile


    fun changeProfile(uri: Uri) {
        val file = uri.toFile()
        val part = MultipartBody.Part.createFormData(
            "picture",
            file.name,
            RequestBody.create(MediaType.parse(myApplication.contentResolver.getType(uri)!!), file)
        )
    }

    fun getProfile() {
        addDisposable(repository.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _profile.value = it
                    LogUtil.e(TAG, "프로필 가져오기 성공")
                },
                {
                    LogUtil.e(TAG, "프로필 가져오기 실패", it)
                    myApplication.toastMakeToast("프로필 가져오기 실패")
                }
            ))
    }
}