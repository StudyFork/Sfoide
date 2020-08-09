package com.studyfork.sfoide.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

private const val THUMBNAIL_VALUE = 0.2f

fun ImageView.loadCircleImage(
    imageUrl: String?
) {
    Glide.with(context)
        .load(imageUrl ?: "")
        .thumbnail(THUMBNAIL_VALUE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .circleCrop()
        .into(this)
}