package com.studyfork.sfoide.ui.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .centerCrop()
        .circleCrop()
        .into(this)
}