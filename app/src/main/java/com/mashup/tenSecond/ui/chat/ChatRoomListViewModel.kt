package com.mashup.tenSecond.ui.chat

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.ChatRoom
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.Event
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatRoomListViewModel(val repository: Repository, val myApplication: Application) :
    ApplicationViewModel(myApplication) {
    val TAG = "ChatRoomListViewModel"
    private val _chatRoomList: MutableLiveData<MutableList<ChatRoom>> = MutableLiveData()
    val chatRoomList: LiveData<MutableList<ChatRoom>> get() = _chatRoomList

    private val _chatRoom: MutableLiveData<Event<ChatRoom>> = MutableLiveData()
    val chatRoom: LiveData<Event<ChatRoom>> get() = _chatRoom

    fun getChatRoomList() {
        addDisposable(repository.getChatRoomList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _chatRoomList.value = it
                    LogUtil.e(TAG, "채팅방 목록 가져오기 성공 ${it}")
                },
                {
                    LogUtil.e(TAG, "채팅방 목록 가져오기 실패", it)
                    myApplication.toastMakeToast("채팅방 목록 가져오기 실패")
                }
            )
        )
    }

    fun clickItem(view: View, event: ChatRoom) {
        _chatRoom.value = Event(event)
    }


}