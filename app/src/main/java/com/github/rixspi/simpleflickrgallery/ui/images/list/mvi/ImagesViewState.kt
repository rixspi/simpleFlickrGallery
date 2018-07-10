package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.mvibase.MviViewState


data class ImagesViewState(
        val isLoading: Boolean,
        val images: List<Image>,
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