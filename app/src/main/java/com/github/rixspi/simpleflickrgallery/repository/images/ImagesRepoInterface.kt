package com.github.rixspi.simpleflickrgallery.repository.images


import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import io.reactivex.Single


interface ImagesRepoInterface {
    fun getImages(): Single<List<Image>>

    fun addImageToFav(): Single<Image>
}