package com.github.rixspi.simpleflickrgallery.utils.view

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView


@BindingAdapter("visibleIf")
fun changeVisibility(view: View?, visible: Boolean) {
    view?.let { it.visibility = if (visible) View.VISIBLE else View.GONE }
}

@BindingAdapter("externalImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.loadImage(imageUrl)
}