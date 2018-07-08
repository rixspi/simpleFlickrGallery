package com.github.rixspi.simpleflickrgallery.utils.view

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.target.Target
import com.github.rixspi.simpleflickrgallery.di.base.GlideApp


fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflateLayout(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)!!

fun ImageView.loadImage(url: String?,
                        @DrawableRes placeholderResId: Int? = null,
                        @DrawableRes errorResId: Int? = null,
                        vararg additionalTransformations: BitmapTransformation,
                        height: Int? = null,
                        animate: Boolean = true) {
    if (url.isNullOrEmpty())
        errorResId?.let { setImageResource(it) }
                ?: placeholderResId?.let { setImageResource(it) }
                ?: setImageDrawable(null)
    else GlideApp
            .with(this.context)
            .load(url)
            .apply { if (animate.not()) dontAnimate() }
            .apply { height?.let { override(Target.SIZE_ORIGINAL, height) } }
            .apply { placeholderResId?.let { placeholder(it) } }
            .apply { errorResId?.let { error(it) } }
            .apply {
                if (additionalTransformations.isNotEmpty()) {
                    transforms(additionalTransformations)
                }
            }
            .into(this)

}

fun <T, R : RecyclerView.ViewHolder>
        RecyclerView.Adapter<R>.applyObjectsChangesToList(old: List<T>,
                                                          new: List<T>,
                                                          compare: (T, T) -> Boolean) =
        DiffUtil.calculateDiff(
                object : DiffUtil.Callback() {

                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            compare(old[oldItemPosition], new[newItemPosition])

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            old[oldItemPosition] == new[newItemPosition]

                    override fun getOldListSize() = old.size
                    override fun getNewListSize() = new.size
                }
        ).dispatchUpdatesTo(this)