package com.github.rixspi.simpleflickrgallery.ui.images.details.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviIntent


sealed class ImageDetailsIntent: MviIntent {
    data class InitialIntent(val id: String): ImageDetailsIntent()
}