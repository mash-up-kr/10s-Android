package com.namget.diaryLee.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Job

abstract class BaseFragment<B : ViewDataBinding> : androidx.fragment.app.Fragment() {
    open protected lateinit var binding: B
    abstract fun onLayoutId(): Int
    abstract fun initViewModel()

    private val compositeDisposable = CompositeDisposable()
    private val jobs = arrayListOf<Job>()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, onLayoutId(), container, false)
        initViewModel()
        return binding.root
    }

    override fun onDestroyView() {
        for (job in jobs) {
            if (job.isActive) {
                job.cancel()
            }
        }
        compositeDisposable.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}