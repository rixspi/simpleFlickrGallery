package com.github.rixspi.simpleflickrgallery.ui.images.list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import com.github.rixspi.simpleflickrgallery.mvibase.*
import com.github.rixspi.simpleflickrgallery.ui.base.BaseViewModel
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.*
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage
import com.github.rixspi.simpleflickrgallery.utils.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ImagesListViewModel @Inject constructor(
        private val actionProcessor: ImagesActionProcessorHolder
) : BaseViewModel(), MviViewModel<ImagesIntent, ImagesViewState> {

    val loading = ObservableBoolean()

    val items = ObservableArrayList<UiImage>()

    /**
     * Proxy subject used to keep the stream alive even after the UI gets recycled.
     * This is basically used to keep ongoing events and the last cached State alive
     * while the UI disconnects and reconnects on config changes.
     */
    private val intentsSubject: PublishSubject<ImagesIntent> = PublishSubject.create()
    private val statesObservable: Observable<ImagesViewState> = compose()

    /**
     * take only the first ever InitialIntent and all intents of other types
     * to avoid reloading data on config changes
     */
    private val intentFilter: ObservableTransformer<ImagesIntent, ImagesIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                        shared.ofType(ImagesIntent.InitialIntent::class.java).take(1),
                        shared.notOfType(ImagesIntent.InitialIntent::class.java)
                )
            }
        }

    override fun processIntents(intents: Observable<ImagesIntent>) = intents.subscribe(intentsSubject)

    override fun states(): Observable<ImagesViewState> = statesObservable

    /**
     * Compose all components to create the stream logic
     */
    private fun compose(): Observable<ImagesViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map<ImagesAction>(this::actionFromIntent)
                .compose(actionProcessor.actionProcessor)
                // Cache each state and pass it to the reducer to create a new state from
                // the previous cached one and the latest Result emitted from the action processor.
                // The Scan operator is used here for the caching.
                .scan(ImagesViewState.idle(), reducer)
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

    /**
     * Translate an [MviIntent] to an [MviAction].
     * Used to decouple the UI and the business logic to allow easy testings and reusability.
     */
    private fun actionFromIntent(intent: ImagesIntent): ImagesAction {
        return when (intent) {
            is ImagesIntent.InitialIntent -> ImagesAction.LoadImagesAction(true)
            is ImagesIntent.RefreshIntent -> ImagesAction.LoadImagesAction(intent.update)
        }
    }

    fun handleAndroidDataBinding(state: ImagesViewState) {
        loading.set(state.isLoading)

        if (items.toList() != state.images) {
            items.clear()
            items.addAll(state.images)
        }
    }

    companion object {
        /**
         * The Reducer is where [MviViewState], that the [MviView] will use to
         * render itself, are created.
         * It takes the last cached [MviViewState], the latest [MviResult] and
         * creates a new [MviViewState] by only updating the related fields.
         * This is basically like a big switch statement of all possible types for the [MviResult]
         */
        private val reducer = BiFunction { previousState: ImagesViewState, result: ImagesResult ->
            when (result) {
                is ImagesResult.LoadImagesResult -> when (result) {
                    is ImagesResult.LoadImagesResult.Success -> {
                        previousState.copy(
                                isLoading = false,
                                images = result.images
                        )
                    }
                    is ImagesResult.LoadImagesResult.Failure -> previousState.copy(isLoading = false, error = result.error)
                    is ImagesResult.LoadImagesResult.InProgress -> previousState.copy(isLoading = true)
                }
            }

        }
    }
}