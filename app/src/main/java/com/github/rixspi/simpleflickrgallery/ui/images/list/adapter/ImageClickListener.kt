package com.github.rixspi.simpleflickrgallery.ui.images.list.adapter

import android.view.View
import com.github.rixspi.simpleflickrgallery.repository.images.model.Image


interface ImageClickListener {
    fun imageClicked(item: Image, view: View? = null)
    fun favClicked(item: Image)
}