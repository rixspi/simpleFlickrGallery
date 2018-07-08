package com.github.rixspi.simpleflickrgallery.ui.main.mvi

import com.github.rixspi.simpleflickrgallery.data.Image
import com.github.rixspi.simpleflickrgallery.mvibase.MviAction


sealed class ImagesAction : MviAction {
    data class LoadImagesAction(
            val update: Boolean
    ) : ImagesAction()

    data class AddToFavAction(val image: Image) : ImagesAction()
}