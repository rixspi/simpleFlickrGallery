package com.github.rixspi.simpleflickrgallery.repository.net.model

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image


data class ImagesApiResponse(
        val title: String? = null,
        val link: String? = null,
        val description: String? = null,
        val modified: String? = null,
        val items: List<Image>? = null
)