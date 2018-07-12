package com.github.rixspi.simpleflickrgallery.di.images.details

import com.github.rixspi.simpleflickrgallery.di.base.scope.FragmentScope
import com.github.rixspi.simpleflickrgallery.ui.images.details.ImageDetailsFragment
import dagger.Module
import dagger.Provides


@Module
class ImageDetailsModule(private val fragment: ImageDetailsFragment) {
    @Provides
    @FragmentScope
    fun provideFragment(): ImageDetailsFragment = fragment
}