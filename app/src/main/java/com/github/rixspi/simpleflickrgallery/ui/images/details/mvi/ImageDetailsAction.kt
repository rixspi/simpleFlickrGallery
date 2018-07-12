package com.github.rixspi.simpleflickrgallery.ui.images.details.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviAction


sealed class ImageDetailsAction : MviAction {
    data class LoadImageAction(val id: String?) : ImageDetailsAction()
}