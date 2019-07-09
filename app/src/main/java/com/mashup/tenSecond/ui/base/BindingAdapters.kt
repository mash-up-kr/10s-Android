package com.mashup.tenSecond.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:glideImage")
fun ImageView.setGlideImage(url: String) {
    setGlideImage(url)
}