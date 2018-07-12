package com.github.rixspi.simpleflickrgallery.repository.images

import android.support.annotation.VisibleForTesting
import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*


class ImagesRepository(
        private val flickrApi: FlickrApi) : ImagesRepoInterface {

    @VisibleForTesting
    var cachedTasks: MutableMap<String, Image>? = null
    @VisibleForTesting
    var cacheIsDirty = false

    private fun getAndSaveRemoteImages(): Single<List<Image>> {
        return flickrApi.getImages()
                .flatMap { images ->
                    Observable.fromIterable(images.items)
                            .doOnNext { image ->
                                cachedTasks!![image.id()] = image
                            }.toList()
                }
                .doOnSuccess { cacheIsDirty = false }
    }

    override fun getImages(): Single<List<Image>> {
        if (cachedTasks != null && !cacheIsDirty) {
            return Observable.fromIterable(cachedTasks!!.values).toList()
        } else if (cachedTasks == null) {
            cachedTasks = LinkedHashMap()
        }
        return getAndSaveRemoteImages()
    }

    override fun refreshImages() {
        cacheIsDirty = true
        //Clearing because of refreshing nature of flickr feed
        cachedTasks?.clear()
    }


    override fun addImageToFav(): Single<Image> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}