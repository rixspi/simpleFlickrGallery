package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ImagesActionProcessorHolder @Inject constructor(
        private val imagesRepo: ImagesRepoInterface,
        private val imageMapper: RepoImageMapper
) {
    internal var actionProcessor =
            ObservableTransformer<ImagesAction, ImagesResult> { actions ->
                actions.publish { shared ->
                    shared.ofType(ImagesAction.LoadImagesAction::class.java).compose(loadTasksProcessor)
                            .cast(ImagesResult::class.java)
                            // Match ActivateTaskAction to populateTaskProcessor
                            .mergeWith(
                                    // Error for not implemented actions
                                    shared.filter { v ->
                                        v !is ImagesAction.LoadImagesAction
                                    }.flatMap { w ->
                                        Observable.error<ImagesResult>(
                                                IllegalArgumentException("Unknown Action type: $w"))
                                    }
                            )
                }
            }


    private val loadTasksProcessor =
            ObservableTransformer<ImagesAction.LoadImagesAction, ImagesResult.LoadImagesResult> { actions ->
                actions.flatMap { action ->
                    imagesRepo.getImages(action.update)
                            // Transform the Single to an Observable to allow emission of multiple
                            // events down the stream (e.g. the InFlight event)
                            .toObservable()
                            // Wrap returned data into an immutable object
                            .map { ImagesResult.LoadImagesResult.Success(imageMapper.mapToEntity(it)) }
                            .cast(ImagesResult.LoadImagesResult::class.java)
                            // Wrap any error into an immutable object and pass it down the stream
                            // without crashing.
                            // Because errors are data and hence, should just be part of the stream.
                            .onErrorReturn(ImagesResult.LoadImagesResult::Failure)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            // Emit an InFlight event to notify the subscribers (e.g. the UI) we are
                            // doing work and waiting on a response.
                            // We emit it after observing on the UI thread to allow the event to be emitted
                            // on the current frame and avoid jank.
                            .startWith(ImagesResult.LoadImagesResult.InProgress)
                }
            }
}