package com.github.rixspi.simpleflickrgallery.di.images.list

import com.github.rixspi.simpleflickrgallery.di.base.scope.FragmentScope
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListFragment
import dagger.Module
import dagger.Provides

@Module
class ImagesListModule(private val fragment: ImagesListFragment) {
    @Provides
    @FragmentScope
    fun provideFragment(): ImagesListFragment = fragment
}