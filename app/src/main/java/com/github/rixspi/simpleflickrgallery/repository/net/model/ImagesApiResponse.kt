package com.github.rixspi.simpleflickrgallery.repository.net.model

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image


data class ImagesApiResponse(
        val title: String?,
        val link: String?,
        val description: String?,
        val modified: String?,
        val items: List<Image>
)