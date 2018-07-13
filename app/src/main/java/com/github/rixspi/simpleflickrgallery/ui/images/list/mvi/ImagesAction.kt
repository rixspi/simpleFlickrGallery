package com.github.rixspi.simpleflickrgallery.ui.images.list.mvi

import com.github.rixspi.simpleflickrgallery.mvibase.MviAction


sealed class ImagesAction : MviAction {
    data class LoadImagesAction(
            val update: Boolean
    ) : ImagesAction()
}