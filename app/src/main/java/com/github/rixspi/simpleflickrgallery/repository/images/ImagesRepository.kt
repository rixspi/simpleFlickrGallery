package com.github.rixspi.simpleflickrgallery.repository.images

import android.support.annotation.VisibleForTesting
import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import kotlin.NoSuchElementException


class ImagesRepository(
        private val flickrApi: FlickrApi) : ImagesRepoInterface {

    @VisibleForTesting
    var cachedImages: MutableMap<String, Image>? = null
    @VisibleForTesting
    var cacheIsDirty = false

    private fun getAndSaveRemoteImages(): Single<List<Image>> {
        return flickrApi.getImages()
                .flatMap { images ->
                    Observable.fromIterable(images.items)
                            .doOnNext { image ->
                                cachedImages!![image.id()] = image
                            }.toList()
                }
                .doOnSuccess { cacheIsDirty = false }
    }

    override fun getImages(): Single<List<Image>> {
        if (cachedImages != null && !cacheIsDirty) {
            return Observable.fromIterable(cachedImages!!.values).toList()
        } else if (cachedImages == null) {
            cachedImages = LinkedHashMap()
        }
        return getAndSaveRemoteImages()
    }

    override fun refreshImages() {
        cacheIsDirty = true
        //Clearing because of refreshing nature of flickr feed
        cachedImages?.clear()
    }

    override fun getImageFromCache(imageId: String): Single<Image> {
        return cachedImages?.get(imageId)?.let {
            Single.just(it)
        } ?: run {
            Single.error<Image>(NoSuchElementException("Image wasn't found in the cache!"))
        }
    }

}