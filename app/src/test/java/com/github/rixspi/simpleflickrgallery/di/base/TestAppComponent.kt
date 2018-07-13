package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepositoryTest
import com.github.rixspi.simpleflickrgallery.ui.images.details.ImageDetailsViewModelTest
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListViewModelTest
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(TestAppModule::class), (TestNetModule::class)])
interface TestAppComponent {
    fun inject(imagesRepository: ImagesRepositoryTest): ImagesRepositoryTest

    fun inject(imagesListViewModelTest: ImagesListViewModelTest): ImagesListViewModelTest

    fun inject(imageDetailsViewModelTest: ImageDetailsViewModelTest): ImageDetailsViewModelTest
}