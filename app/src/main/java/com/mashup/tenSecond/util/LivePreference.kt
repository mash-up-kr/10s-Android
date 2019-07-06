package com.mashup.tenSecond.util

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

@Suppress("UNCHECKED_CAST")
class LivePreference<T> constructor(updates: Observable<String>,
                                    private val preferences: SharedPreferences,
                                    private val key: String,
                                    private val defaultValue: T) : MutableLiveData<T>() {

    private val disposable = CompositeDisposable()

    init {
        value = (preferences.all[key] as T) ?: defaultValue

        disposable.add(updates.filter { t -> t == key }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableObserver<String>() {
                override fun onComplete() {

                }

                override fun onNext(t: String) {
                    postValue((preferences.all[t] as T) ?: defaultValue)
                }

                override fun onError(e: Throwable) {

                }
            }))
    }

    override fun onInactive() {
        super.onInactive()
        disposable.dispose()
    }
}