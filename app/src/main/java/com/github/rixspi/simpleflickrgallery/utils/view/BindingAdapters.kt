package com.github.rixspi.simpleflickrgallery.utils.view

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.github.rixspi.simpleflickrgallery.R


@BindingAdapter("visibleIf")
fun changeVisibility(view: View?, visible: Boolean) {
    view?.let { it.visibility = if (visible) View.VISIBLE else View.GONE }
}

@BindingAdapter("visibleIfNotEmpty")
fun changeVisibilityDependingOnStringContent(view: View?, text: String?) {
    view?.let { it.visibility = if (text.isNullOrBlank().not()) View.VISIBLE else View.GONE }
}

@BindingAdapter("externalImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.loadImage(imageUrl)
}

@BindingAdapter("thumbnailImage")
fun loadImageThumbnail(view: ImageView, imageUrl: String?) {
    view.context
            .resources
            .getDimensionPixelSize(R.dimen.padding_default)
            .let {
                view.loadImage(
                        imageUrl,
                        placeholderResId = R.drawable.ic_launcher_background
                )
            }
}