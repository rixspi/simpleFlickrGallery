package com.github.rixspi.simpleflickrgallery.ui.images.details

import com.github.rixspi.simpleflickrgallery.mvibase.MviViewModel
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.base.BaseViewModel
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsAction
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsIntent
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsResult
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsViewState
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import com.github.rixspi.simpleflickrgallery.utils.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ImageDetailsViewModel @Inject constructor(
        private val imagesRepo: ImagesRepoInterface,
        private val imageMapper: RepoImageMapper
) : BaseViewModel(), MviViewModel<ImageDetailsIntent, ImageDetailsViewState> {

    private val intentsSubject: PublishSubject<ImageDetailsIntent> = PublishSubject.create()
    private val statesObservable: Observable<ImageDetailsViewState> = compose()

    override fun processIntents(intents: Observable<ImageDetailsIntent>) = intents.subscribe(intentsSubject)

    override fun states(): Observable<ImageDetailsViewState> = statesObservable

    private val intentFilter: ObservableTransformer<ImageDetailsIntent, ImageDetailsIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                        shared.ofType(ImageDetailsIntent.InitialIntent::class.java).take(1),
                        shared.notOfType(ImageDetailsIntent.InitialIntent::class.java)
                )
            }
        }


    private fun compose(): Observable<ImageDetailsViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map(this::actionFromIntent)
                .compose(actionProcessor)
                // Cache each state and pass it to the reducer to create a new state from
                // the previous cached one and the latest Result emitted from the action processor.
                // The Scan operator is used here for the caching.
                .scan(ImageDetailsViewState.idle(), reducer)
                // When a reducer just emits previousState, there's no reason to call render. In fact,
                // redrawing the UI in cases like this can cause jank (e.g. messing up snackbar animations
                // by showing the same snackbar twice in rapid succession).
                .distinctUntilChanged()
                // Emit the last one event of the stream on subscription
                // Useful when a View rebinds to the ViewModel after rotation.
                .replay(1)
                // Create the stream on creation without waiting for anyone to subscribe
                // This allows the stream to stay alive even when the UI disconnects and
                // match the stream's lifecycle to the ViewModel's one.
                .autoConnect(0)
    }

    private fun actionFromIntent(intent: ImageDetailsIntent): ImageDetailsAction.LoadImageAction {
        return when (intent) {
            is ImageDetailsIntent.InitialIntent -> ImageDetailsAction.LoadImageAction(intent.id)
        }
    }


    private var actionProcessor =
            ObservableTransformer<ImageDetailsAction.LoadImageAction, ImageDetailsResult.LoadImageResult> { actions ->
                actions.publish { shared ->
                    shared.compose(loadImageProcessor)
                }
            }

    private val loadImageProcessor =
            ObservableTransformer<ImageDetailsAction.LoadImageAction, ImageDetailsResult.LoadImageResult> { actions ->
                actions.flatMap { action ->
                    imagesRepo.getImageFromCache(action.id)
                            .toObservable()
                            .map { ImageDetailsResult.LoadImageResult.Success(imageMapper.mapToEntity(it)) }
                            .cast(ImageDetailsResult.LoadImageResult::class.java)
                            .onErrorReturn(ImageDetailsResult.LoadImageResult::Failure)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .startWith(ImageDetailsResult.LoadImageResult.InProgress)
                }
            }

    companion object {

        private val reducer = BiFunction { previousState: ImageDetailsViewState, result: ImageDetailsResult ->
            when (result) {
                is ImageDetailsResult.LoadImageResult -> when (result) {
                    is ImageDetailsResult.LoadImageResult.Success -> {
                        previousState.copy(
                                isLoading = false,
                                image = result.image
                        )
                    }
                    is ImageDetailsResult.LoadImageResult.Failure -> previousState.copy(isLoading = false, error = result.error)
                    is ImageDetailsResult.LoadImageResult.InProgress -> previousState.copy(isLoading = true)
                }
            }
        }
    }
}