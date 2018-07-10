package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.di.images.list.ImagesListComponent
import com.github.rixspi.simpleflickrgallery.di.images.list.ImagesListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetModule::class),
    (ViewModelModule::class)])
interface AppComponent {

    fun plus(module: ImagesListModule): ImagesListComponent
}