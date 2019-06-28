package com.namget.diaryLee.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDialog<B : ViewDataBinding>(context: Context) : Dialog(context) {

    open protected lateinit var binding: B
    abstract fun onLayoutId(): Int



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), onLayoutId(), null, false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
    }



}
