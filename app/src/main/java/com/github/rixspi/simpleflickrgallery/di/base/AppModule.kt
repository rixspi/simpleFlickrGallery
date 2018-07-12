package com.github.rixspi.simpleflickrgallery.di.base


import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepository
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {
    @Provides
    fun getContext(): Context = application.applicationContext

    @Provides
    fun getResources(): Resources = application.resources

    @Singleton
    @Provides
    fun getImagesRepository(flickrApi: FlickrApi): ImagesRepoInterface = ImagesRepository(flickrApi)
}