package com.github.rixspi.simpleflickrgallery.repository.images

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Single


class ImagesRepository(
        private val flickrApi: FlickrApi
): ImagesRepoInterface{
    override fun getImages(): Single<List<Image>> =
            flickrApi
                    .getImages()
                    .map { it.items }



    override fun addImageToFav(): Single<Image> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}