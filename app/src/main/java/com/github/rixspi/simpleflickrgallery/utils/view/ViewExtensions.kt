package com.github.rixspi.simpleflickrgallery.utils.view

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.rixspi.simpleflickrgallery.di.base.GlideApp


fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflateLayout(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)!!

fun ImageView.loadImage(url: String?, @DrawableRes placeholderResId: Int? = null, @DrawableRes errorResId: Int? = null) {
    if (url.isNullOrEmpty()) errorResId?.let { setImageResource(it) }
            ?: placeholderResId?.let { setImageResource(it) }
    else GlideApp
            .with(this.context)
            .load(url)
            .apply { placeholderResId?.let { placeholder(it) } }
            .apply { errorResId?.let { error(it) } }
            .into(this)
}

fun <T> RecyclerView.Adapter<*>.autoNotify(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(old[oldItemPosition], new[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size
    })

    diff.dispatchUpdatesTo(this)
}