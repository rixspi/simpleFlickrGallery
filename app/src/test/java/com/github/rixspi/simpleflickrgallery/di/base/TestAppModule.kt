package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
class TestAppModule {
    @Provides
    fun provideImagesRepo() = mock(ImagesRepository::class.java)
}