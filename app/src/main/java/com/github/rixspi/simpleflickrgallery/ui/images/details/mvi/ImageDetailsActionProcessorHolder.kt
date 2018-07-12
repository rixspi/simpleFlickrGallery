package com.github.rixspi.simpleflickrgallery.ui.images.details.mvi

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ImageDetailsActionProcessorHolder @Inject constructor(
        private val imagesRepo: ImagesRepoInterface,
        private val imageMapper: RepoImageMapper
) {
    private val loadImageProcessor =
            ObservableTransformer<ImageDetailsAction.LoadImageAction, ImageDetailsResult.LoadImageResult> { actions ->
                actions.flatMap { action ->
                    imagesRepo.getImageFromCache(action.id!!)
                            .toObservable()
                            .map { ImageDetailsResult.LoadImageResult.Success(imageMapper.mapToEntity(it)) }
                            .cast(ImageDetailsResult.LoadImageResult::class.java)
                            .onErrorReturn(ImageDetailsResult.LoadImageResult::Failure)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .startWith(ImageDetailsResult.LoadImageResult.InProgress)
                }
            }

    internal var actionProcessor =
            ObservableTransformer<ImageDetailsAction, ImageDetailsResult> { actions ->
                actions.publish { shared ->
                    shared.ofType(ImageDetailsAction.LoadImageAction::class.java)
                            .compose(loadImageProcessor)
                            .cast(ImageDetailsResult::class.java)
                            .mergeWith(
                                    shared.filter { v ->
                                        v !is ImageDetailsAction.LoadImageAction
                                    }.flatMap { w ->
                                        Observable.error<ImageDetailsResult>(
                                                IllegalArgumentException("Unknown Action type: $w"))
                                    }
                            )
                }
            }
}