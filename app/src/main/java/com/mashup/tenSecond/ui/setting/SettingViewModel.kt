package com.mashup.tenSecond.ui.setting

import android.app.Application
import android.content.ContentValues.TAG
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mashup.tenSecond.data.model.Profile
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.data.repository.request.ProfileRequest
import com.mashup.tenSecond.ui.base.ApplicationViewModel
import com.mashup.tenSecond.util.LogUtil
import com.mashup.tenSecond.util.toastMakeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import android.R.attr.data
import androidx.room.util.CursorUtil.getColumnIndex


class SettingViewModel(val repository: Repository, val myApplication: Application) :
    ApplicationViewModel(myApplication) {


    private val _profile: MutableLiveData<Profile> = MutableLiveData()
    val profile: LiveData<Profile> get() = _profile

    private val _id: MutableLiveData<String> = MutableLiveData()
    val id: LiveData<String> get() = _id

    private val _status: MutableLiveData<String> = MutableLiveData()
    val status: LiveData<String> get() = _status

    private val _partData: MutableLiveData<MultipartBody.Part> = MutableLiveData()
    val partData: LiveData<MultipartBody.Part>
        get() = _partData


    fun changeProfile(uri: Uri) {

        val file = getRealPathFromURI(uri)

        LogUtil.e("filePath", "file")
        LogUtil.e("fileType", myApplication.contentResolver.getType(uri))

        _partData.value = MultipartBody.Part.createFormData(
            "upload",
            null,
            RequestBody.create(MediaType.parse(myApplication.contentResolver.getType(uri)!!), file)
        )
    }


    fun getRealPathFromURI(contentUri: Uri): File {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = myApplication.getContentResolver().query(contentUri, proj, null, null, null)
        cursor.moveToNext()
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
        cursor.close()

        return File(path)
    }


    fun getProfile() {
        addDisposable(repository.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _profile.value = it
                    _id.value = it.nickname
                    _status.value = it.status
                    LogUtil.e(TAG, "프로필 가져오기 성공")
                },
                {
                    LogUtil.e(TAG, "프로필 가져오기 실패", it)
                    myApplication.toastMakeToast("프로필 가져오기 실패")
                }
            ))
    }

    fun checkValid(): Boolean = (id.value?.isNotEmpty() == false && status.value?.isNotEmpty() == false)

    fun changeProfile() {
        val map: MutableMap<String, RequestBody> = HashMap()
        val email = RequestBody.create(MediaType.parse("text/plain"), id.value!!)
        val nickname = RequestBody.create(MediaType.parse("text/plain"), status.value!!)
        map["email"] = email
        map["nickname"] = nickname

        addDisposable(
            repository.changeProfile(
                map,
                partData.value!!
            ).subscribe({
                if (it.message == "user updated") {
                    myApplication.toastMakeToast("프로필 저장하기 성공")
                    LogUtil.e("changeProfile", "test")
                }
            }, {
                myApplication.toastMakeToast("프로필 저장하기 실패")
                LogUtil.e("changeProfile", "test", it)
            })
        )
    }

    fun save(view: View) {
        //ID 공백일 경우 저장x
        if (checkValid()) {
            view.context.toastMakeToast("공백을 채워주세요")
            return
        }
        changeProfile()

    }

    fun idTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            _id.value = s.toString()
        }
    }

    fun statusChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isEmpty()) {
            _status.value = s.toString()
        }
    }


}