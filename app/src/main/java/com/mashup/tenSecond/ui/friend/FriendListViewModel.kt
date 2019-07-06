package com.mashup.tenSecond.ui.friend

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.FriendList
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.util.Event
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class FriendListViewModel(
    val repository: Repository,
    val myApplication: Application
) :
    ApplicationViewModel(myApplication) {
    val TAG = "FriendListViewModel"


    private val _friendList: MutableLiveData<FriendList> = MutableLiveData()
    val friendList: LiveData<FriendList> get() = _friendList

    private val _event: MutableLiveData<Event<String>> = MutableLiveData()
    val event: LiveData<Event<String>> get() = _event

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun getFriendList() {
        repository.getFriendList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _friendList.value = it
                    LogUtil.e(TAG, "친구목록 가져오기 성공 ${it}")
                },
                {
                    LogUtil.e(TAG, "친구목록 가져오기 실패",it)
                    myApplication.toastMakeToast("친구목록 가져오기 실패")
                }
            )
    }

    fun addFriendList(email: String) {
        repository.addFriendList(email)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    LogUtil.e("TAG,", "서버 요청 성공 ${it}")
                },
                {
                    LogUtil.e("TAG,", "서버 요청 실패 ${it}")
                    myApplication.toastMakeToast("서버 요청 실패")
                }
            )
    }

    fun deleteFriendList() {

    }


}