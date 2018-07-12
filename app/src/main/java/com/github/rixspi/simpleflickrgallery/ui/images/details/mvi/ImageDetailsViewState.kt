package com.github.rixspi.simpleflickrgallery.ui.images.details.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviViewState
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage


data class ImageDetailsViewState(
        val isLoading: Boolean,
        val image: UiImage?,
        val error: Throwable?
) : MviViewState {
    
    companion object {
        fun idle(): ImageDetailsViewState {
            return ImageDetailsViewState(
                    isLoading = false,
                    image = null,
                    error = null
            )
        }
    }
}