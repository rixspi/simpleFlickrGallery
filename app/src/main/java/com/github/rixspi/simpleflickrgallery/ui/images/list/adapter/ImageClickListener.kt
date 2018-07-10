package com.github.rixspi.simpleflickrgallery.ui.images.list.adapter

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image


interface ImageClickListener {
    fun imageClicked(item: Image)
    fun favClicked(item: Image)
}