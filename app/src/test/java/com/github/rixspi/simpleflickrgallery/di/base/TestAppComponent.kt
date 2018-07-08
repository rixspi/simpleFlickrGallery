package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepositoryTest
import com.github.rixspi.simpleflickrgallery.ui.main.MainAcitivtyViewModelTest
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(TestAppModule::class), (TestNetModule::class)])
interface TestAppComponent {
    fun inject(imagesRepository: ImagesRepositoryTest): ImagesRepositoryTest

    fun inject(mainActivityViewModel: MainAcitivtyViewModelTest): MainAcitivtyViewModelTest
}