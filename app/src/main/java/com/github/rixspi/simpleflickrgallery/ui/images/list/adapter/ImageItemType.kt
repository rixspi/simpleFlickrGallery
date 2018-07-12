package com.github.rixspi.simpleflickrgallery.ui.images.list.adapter

import android.support.v4.view.ViewCompat
import android.view.View
import com.github.nitrico.lastadapter.Holder
import com.github.nitrico.lastadapter.ItemType
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.VhImageBinding
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage


class ImageItemType(private val clickListener: ImageClickListener<UiImage, View>) : ItemType<VhImageBinding>(R.layout.vh_image) {

    override fun onCreate(holder: Holder<VhImageBinding>) {
        with(holder) {
            itemView.setOnClickListener {
                clickListener
            }
        }
        holder.itemView.setOnClickListener {
            holder.binding.item?.let {
                clickListener.invoke(it, holder.binding.ivImage)
            }
        }
        super.onCreate(holder)
    }

    override fun onBind(holder: Holder<VhImageBinding>) {
        with(holder) {
            val transitionName = itemView.context.getString(R.string.image_transition_name, binding.item?.id)
            ViewCompat.setTransitionName(binding.ivImage, transitionName)
        }

        super.onBind(holder)
    }
}