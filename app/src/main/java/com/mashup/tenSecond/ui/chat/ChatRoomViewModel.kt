package com.mashup.tenSecond.ui.chat

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.Messages
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatRoomViewModel(val repository: Repository, val myApplication: Application) :
    ApplicationViewModel(myApplication) {
    val TAG = "ChatRoomListViewModel"
    private val _chatMessage: MutableLiveData<List<Messages.Message>> = MutableLiveData()
    val chatMessage: LiveData<List<Messages.Message>> get() = _chatMessage


    fun getChatRoom(id: Int) {
        addDisposable(repository.getChatRoomById(id,0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _chatMessage.value = it.messages
                    LogUtil.e(TAG, "채팅 내용 가져오기 성공 ${it}")
                },
                {
                    LogUtil.e(TAG, "채팅 내용 가져오기 실패", it)
                    myApplication.toastMakeToast("채팅 내용 가져오기 실패")
                }
            )
        )
    }


}