package com.github.rixspi.simpleflickrgallery.di.images.details

import com.github.rixspi.simpleflickrgallery.di.base.scope.FragmentScope
import com.github.rixspi.simpleflickrgallery.ui.images.details.ImageDetailsFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [
    (ImageDetailsModule::class)
])
interface ImageDetailsComponent {
    fun inject(fragment: ImageDetailsFragment): ImageDetailsFragment
}