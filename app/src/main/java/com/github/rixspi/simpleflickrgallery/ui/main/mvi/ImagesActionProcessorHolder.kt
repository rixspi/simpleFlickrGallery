package com.github.rixspi.simpleflickrgallery.ui.main.mvi

import com.github.rixspi.simpleflickrgallery.data.repo.FakeImagesRepo
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ImagesActionProcessorHolder @Inject constructor(
        private val imagesRepo: FakeImagesRepo
) {
    internal var actionProcessor =
            ObservableTransformer<ImagesAction, ImagesResult> { actions ->
                actions.publish { shared ->
                    Observable.merge(
                            // Match LoadTasksAction to loadTasksProcessor
                            shared.ofType(ImagesAction.LoadImagesAction::class.java).compose(loadTasksProcessor),
                            // Match ActivateTaskAction to populateTaskProcessor
                            shared.ofType(ImagesAction.AddToFavAction::class.java)
                                    .compose(addImagesToFavProcessor))
                            .mergeWith(
                                    // Error for not implemented actions
                                    shared.filter { v ->
                                        v !is ImagesAction.LoadImagesAction
                                                && v !is ImagesAction.AddToFavAction

                                    }.flatMap { w ->
                                        Observable.error<ImagesResult>(
                                                IllegalArgumentException("Unknown Action type: $w"))
                                    }
                            )
                }
            }


    private val loadTasksProcessor =
            ObservableTransformer<ImagesAction.LoadImagesAction, ImagesResult.LoadImagesResult> { actions ->
                actions.flatMap {
                    imagesRepo.getImages()
                            // Transform the Single to an Observable to allow emission of multiple
                            // events down the stream (e.g. the InFlight event)
                            .toObservable()
                            // Wrap returned data into an immutable object
                            .map { ImagesResult.LoadImagesResult.Success(it) }
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

    private val addImagesToFavProcessor =
            ObservableTransformer<ImagesAction.AddToFavAction, ImagesResult.AddImageToFavResult> { actions ->
                actions.flatMap {
                    imagesRepo.addImageToFav()
                            // Transform the Single to an Observable to allow emission of multiple
                            // events down the stream (e.g. the InFlight event)
                            .toObservable()
                            // Wrap returned data into an immutable object
                            .map { ImagesResult.AddImageToFavResult.Success(it) }
                            .cast(ImagesResult.AddImageToFavResult::class.java)
                            // Wrap any error into an immutable object and pass it down the stream
                            // without crashing.
                            // Because errors are data and hence, should just be part of the stream.
                            .onErrorReturn(ImagesResult.AddImageToFavResult::Failure)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            // Emit an InFlight event to notify the subscribers (e.g. the UI) we are
                            // doing work and waiting on a response.
                            // We emit it after observing on the UI thread to allow the event to be emitted
                            // on the current frame and avoid jank.
                            .startWith(ImagesResult.AddImageToFavResult.InProgress)
                }
            }
}