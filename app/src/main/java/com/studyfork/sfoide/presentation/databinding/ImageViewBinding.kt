package com.studyfork.sfoide.presentation.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.studyfork.sfoide.presentation.extensions.loadCircleImage

@BindingAdapter(value = ["loadImageCircleCrop"])
fun loadImageCircleCrop(
    imageView: ImageView,
    imageUrl: String?
) {
    imageView.loadCircleImage(imageUrl)
}