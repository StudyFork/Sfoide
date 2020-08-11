package com.studyfork.sfoide.presentation.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.studyfork.sfoide.presentation.extensions.loadCircleImage
import com.studyfork.sfoide.presentation.extensions.loadCircleThumbnail

@BindingAdapter(value = ["loadImageCircleCrop"])
fun loadImageCircleCrop(
    imageView: ImageView,
    imageUrl: String?
) {
    imageView.loadCircleImage(imageUrl)
}

@BindingAdapter(value = ["loadThumbnailCircleCrop"])
fun loadThumbnailCircleCrop(
    imageView: ImageView,
    imageUrl: String?
) {
    imageView.loadCircleThumbnail(imageUrl)
}