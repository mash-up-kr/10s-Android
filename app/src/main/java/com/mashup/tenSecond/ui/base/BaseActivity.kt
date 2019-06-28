package com.namget.diaryLee.ui.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Job

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    abstract fun onLayoutId(): Int
    abstract fun setupViewModel()
    open protected lateinit var binding: B
    val jobs = arrayListOf<Job>()

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, onLayoutId())
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        for(job in jobs){
            if(job.isActive){
                job.cancel()
            }
        }
        compositeDisposable.clear()
        super.onDestroy()
    }
}