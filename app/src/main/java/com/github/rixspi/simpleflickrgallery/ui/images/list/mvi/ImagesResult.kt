package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviResult
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage


sealed class ImagesResult : MviResult {
    sealed class LoadImagesResult : ImagesResult() {
        data class Success(val images: List<UiImage>) : LoadImagesResult()
        data class Failure(val error: Throwable) : LoadImagesResult()
        object InProgress : LoadImagesResult()
    }

    sealed class AddImageToFavResult : ImagesResult() {
        data class Success(val image: UiImage) : AddImageToFavResult()
        data class Failure(val error: Throwable) : AddImageToFavResult()
        object InProgress : AddImageToFavResult()
    }
}