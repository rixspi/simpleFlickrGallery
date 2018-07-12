package com.github.rixspi.simpleflickrgallery.ui.images.details.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviResult
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage


sealed class ImageDetailsResult: MviResult{
    sealed class LoadImageResult: ImageDetailsResult(){
        data class Success(val image: UiImage): LoadImageResult()
        data class Failure(val error: Throwable): LoadImageResult()
        object InProgress: LoadImageResult()
    }
}