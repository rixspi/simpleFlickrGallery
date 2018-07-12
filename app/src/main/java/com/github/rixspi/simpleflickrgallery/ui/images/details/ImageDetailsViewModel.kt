package com.github.rixspi.simpleflickrgallery.ui.images.details

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.base.BaseViewModel
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ImageDetailsViewModel @Inject constructor(
        private val imagesRepo: ImagesRepoInterface
) : BaseViewModel() {


    fun getImageFromRepo(id: String, callback: (UiImage) -> Unit) {
        val imagesMapper = RepoImageMapper()

        imagesRepo.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    list
                            .firstOrNull { it.id() == id }
                            ?.let {
                                callback.invoke(imagesMapper.mapToEntity(it))
                            }
                }, {
                    throw IllegalStateException()
                })
    }
}