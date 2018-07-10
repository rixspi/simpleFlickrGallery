package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.mvibase.MviResult


sealed class ImagesResult : MviResult {
    sealed class LoadImagesResult : ImagesResult() {
        data class Success(val images: List<Image>) : LoadImagesResult()
        data class Failure(val error: Throwable) : LoadImagesResult()
        object InProgress : LoadImagesResult()
    }
    sealed class AddImageToFavResult: ImagesResult() {
        data class Success(val image: Image) : AddImageToFavResult()
        data class Failure(val error: Throwable) : AddImageToFavResult()
        object InProgress : AddImageToFavResult()
    }
}