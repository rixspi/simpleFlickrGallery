package com.github.rixspi.simpleflickrgallery.repository.images

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class FakeImagesRepo @Inject constructor(
        private val flickrApi: FlickrApi
) : ImagesRepoInterface {
    override fun getImages(): Single<List<Image>> =
            Single.just(
                    listOf(
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc"),
                            Image("jakiesid", "url", "desc")
                    )
            ).delay(300, TimeUnit.MILLISECONDS)


    override fun addImageToFav(): Single<Image> =
            Single.just(Image("jakiesid", "url", "desc"))

}