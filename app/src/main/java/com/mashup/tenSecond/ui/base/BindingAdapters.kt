package com.mashup.tenSecond.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mashup.tenSecond.util.setImageWithGlide

@BindingAdapter("android:glideImage")
fun ImageView.setGlideImage(url: String) {
    setImageWithGlide(url)
}