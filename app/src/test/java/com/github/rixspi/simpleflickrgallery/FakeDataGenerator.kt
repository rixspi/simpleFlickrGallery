package com.github.rixspi.simpleflickrgallery

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.images.model.Media
import com.github.rixspi.simpleflickrgallery.repository.net.model.ImagesApiResponse
import javax.inject.Inject


class FakeDataGenerator @Inject constructor() {
    val listOfImages = (0..10).map {
        generateImage(
                author = "author$it",
                authorId = "authorId$it",
                title = "title$it",
                description = "desc$it",
                link = "link$it",
                media = Media(m = "mediaLink$it"))
    }

    fun generateImage(
            author: String? = "",
            authorId: String? = "",
            title: String? = "",
            description: String? = "",
            link: String? = "",
            media: Media? = Media("")) =

            Image(
                    author = author,
                    authorId = authorId,
                    title = title,
                    description = description,
                    link = link,
                    media = media
            )

    fun generateImagesApiResponse() =
            ImagesApiResponse(
                    title = "imagesFeed",
                    description = "feed of images",
                    items = listOfImages
            )
}