package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviViewState
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage


data class ImagesViewState(
        val isLoading: Boolean,
        val images: List<UiImage>,
        val error: Throwable?
) : MviViewState {

    companion object {
        fun idle(): ImagesViewState {
            return ImagesViewState(
                    isLoading = false,
                    images = emptyList(),
                    error = null
            )
        }
    }
}