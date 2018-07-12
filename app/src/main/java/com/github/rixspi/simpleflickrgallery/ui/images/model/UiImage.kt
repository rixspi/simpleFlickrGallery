package com.github.rixspi.simpleflickrgallery.ui.images.model

import com.github.rixspi.simpleflickrgallery.ui.base.UiModel


data class UiImage(
        val id: String,
        val url: String,
        val urlToBiggerImage: String? = null,
        val name: String? = null,
        val description: String? = null,
        val author: String? = null,
        val tags: String? = null
) : UiModel