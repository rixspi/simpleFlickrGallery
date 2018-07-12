package com.github.rixspi.simpleflickrgallery.utils.view

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.rixspi.simpleflickrgallery.di.base.GlideApp


fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String?,
                        @DrawableRes placeholderResId: Int? = null,
                        @DrawableRes errorResId: Int? = null,
                        vararg additionalTransformations: BitmapTransformation,
                        animate: Boolean = true) {
    if (url.isNullOrEmpty())
        errorResId?.let { setImageResource(it) }
                ?: placeholderResId?.let { setImageResource(it) }
                ?: setImageDrawable(null)
    else GlideApp
            .with(this.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(800, 800)
            .apply { if (animate.not()) dontAnimate() }
            .apply { placeholderResId?.let { placeholder(it) } }
            .apply { errorResId?.let { error(it) } }
            .apply {
                if (additionalTransformations.isNotEmpty()) {
                    transforms(additionalTransformations)
                }
            }
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun ImageView.setImageWhenDownloaded(
        url: String?
) {
    GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    this@setImageWhenDownloaded.setImageDrawable(resource)
                    return false
                }

            })
            .preload()
}