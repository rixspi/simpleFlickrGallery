package com.github.rixspi.simpleflickrgallery.ui.images.list.adapter

import com.github.nitrico.lastadapter.Holder
import com.github.nitrico.lastadapter.ItemType
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.VhImageBinding


class ImageItemType(private val clickListener: ImageClickListener) : ItemType<VhImageBinding>(R.layout.vh_image) {

    override fun onCreate(holder: Holder<VhImageBinding>) {
        holder.itemView.setOnClickListener {
            holder.binding.item?.let {
                clickListener.imageClicked(it)
            }
        }
        super.onCreate(holder)
    }

    override fun onBind(holder: Holder<VhImageBinding>) {
        super.onBind(holder)
    }
}