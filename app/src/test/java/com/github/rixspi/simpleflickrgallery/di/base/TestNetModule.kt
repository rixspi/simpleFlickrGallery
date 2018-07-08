package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
class TestNetModule {
    @Provides
    fun provideFlickrApi(): FlickrApi = mock(FlickrApi::class.java)

}