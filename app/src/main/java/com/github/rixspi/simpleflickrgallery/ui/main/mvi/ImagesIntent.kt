package com.github.rixspi.simpleflickrgallery.ui.main.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviIntent


sealed class ImagesIntent : MviIntent {
    object InitialIntent : ImagesIntent()

    data class RefreshIntent(val update: Boolean) : ImagesIntent()
}