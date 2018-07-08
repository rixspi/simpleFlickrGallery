package com.github.rixspi.simpleflickrgallery.data.repo

import com.github.rixspi.simpleflickrgallery.data.Image
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class FakeImagesRepo @Inject constructor() {
    fun getImages(): Single<List<Image>> {
        return Single.just(
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
    }

    fun addImageToFav(): Single<Image> =
            Single.just(Image("jakiesid", "url", "desc"))

}