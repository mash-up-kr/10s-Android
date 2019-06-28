package com.namget.diaryLee.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Job

open class DisposableViewModel : ViewModel() {


    private val compositeDisposable = CompositeDisposable()
    private val jobs = arrayListOf<Job>()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        for (job in jobs) {
            if (job.isActive) {
                job.cancel()
            }
        }
        compositeDisposable.clear()
        super.onCleared()
    }
}