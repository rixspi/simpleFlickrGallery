package com.github.rixspi.simpleflickrgallery.ui.images.details

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.github.rixspi.simpleflickrgallery.mvibase.MviViewModel
import com.github.rixspi.simpleflickrgallery.ui.base.BaseViewModel
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.*
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage
import com.github.rixspi.simpleflickrgallery.utils.notOfType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ImageDetailsViewModel @Inject constructor(
        private val actionProcessorHolder: ImageDetailsActionProcessorHolder
) : BaseViewModel(), MviViewModel<ImageDetailsIntent, ImageDetailsViewState> {

    val image = ObservableField<UiImage>()
    val loading = ObservableBoolean()

    private val intentsSubject: PublishSubject<ImageDetailsIntent> = PublishSubject.create()
    private val statesObservable: Observable<ImageDetailsViewState> = compose()

    private val intentFilter: ObservableTransformer<ImageDetailsIntent, ImageDetailsIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge(
                        shared.ofType(ImageDetailsIntent.InitialIntent::class.java).take(1),
                        shared.notOfType(ImageDetailsIntent.InitialIntent::class.java)
                )
            }
        }

    override fun processIntents(intents: Observable<ImageDetailsIntent>) = intents.subscribe(intentsSubject)

    override fun states(): Observable<ImageDetailsViewState> = statesObservable

    private fun compose(): Observable<ImageDetailsViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map<ImageDetailsAction>(this::actionFromIntent)
                .compose(actionProcessorHolder.actionProcessor)
                .scan(ImageDetailsViewState.idle(), reducer)
                .distinctUntilChanged()
                .replay(1)
                .autoConnect(0)
    }

    private fun actionFromIntent(intent: ImageDetailsIntent): ImageDetailsAction {
        return when (intent) {
            is ImageDetailsIntent.InitialIntent -> ImageDetailsAction.LoadImageAction(intent.id)
        }
    }

    fun handleAndroidDataBinding(viewState: ImageDetailsViewState) {
        image.set(viewState.image)
        loading.set(viewState.isLoading)
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