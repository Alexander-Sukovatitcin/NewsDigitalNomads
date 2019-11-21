package com.gmail.sas.newsdigitalnomads.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

fun ImageView.loadGlideImgUrl(url: String) =
    Glide.with(this)
        .load(url)
        .override(Target.SIZE_ORIGINAL)
        .into(this)