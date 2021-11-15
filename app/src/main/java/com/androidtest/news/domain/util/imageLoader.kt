package com.androidtest.news.domain.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun imageLoader(context: Context , url : String? , imageView: ImageView ){

    if (url != null)
        Glide.with(context)
            .load(url)
            .into(imageView)
}