package com.mashup.tenSecond.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mashup.tenSecond.R

fun ImageView.setImageWithGlide(url: String) =
    url.let {
        try {
            Glide.with(context)
                .load(it)
                .apply(
                    RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.mipmap.ic_launcher_round)
                )
                .into(this)
        } catch (e: Exception) {
        }
    }


fun ImageView.setImageWithGlide(resId: Int) =
    resId.let {
        try {
            Glide.with(context)
                .load(it)
                .apply(
                    RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(this)
        } catch (e: Exception) {
        }
    }