package com.github.rixspi.simpleflickrgallery.repository.images


import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import io.reactivex.Single


interface ImagesRepoInterface {

    fun getImages(update: Boolean): Single<List<Image>> {
        if (update) refreshImages()
        return getImages()
    }

    fun getImages(): Single<List<Image>>

    fun getImageFromCache(imageId: String): Single<Image>

    fun refreshImages()
}