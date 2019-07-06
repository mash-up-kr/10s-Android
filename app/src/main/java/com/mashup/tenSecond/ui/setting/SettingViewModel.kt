package com.mashup.tenSecond.ui.setting

import android.app.Application
import android.content.ContentValues.TAG
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingViewModel(val repository: Repository ,val myApplication: Application) : ApplicationViewModel(myApplication) {



    fun getProfile() {
        repository.getProfile()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    LogUtil.e(TAG, "프로필 가져오기 성공")
                },
                {
                    LogUtil.e(TAG, "프로필 가져오기 실패",it)
                    myApplication.toastMakeToast("프로필 가져오기 실패")
                }
            )
    }
}