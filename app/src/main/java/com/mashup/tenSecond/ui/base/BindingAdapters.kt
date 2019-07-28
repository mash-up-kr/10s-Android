package com.mashup.tenSecond.ui.base

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mashup.tenSecond.util.setImageWithGlide

@BindingAdapter("android:glideImage")
fun ImageView.setGlideImage(url: String) {
    setImageWithGlide(url)
}

@BindingAdapter("android:glideImageUri")
fun ImageView.setGlideImage(uri: Uri) {
    setImageWithGlide(uri)
}