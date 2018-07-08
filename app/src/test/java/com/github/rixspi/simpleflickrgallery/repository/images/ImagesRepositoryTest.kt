package com.github.rixspi.simpleflickrgallery.repository.images

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import javax.inject.Inject
import org.mockito.Mockito.`when` as whenever


class ImagesRepositoryTest : BaseTest() {

    @Inject
    lateinit var flickrApi: FlickrApi

    lateinit var imagesRepository: ImagesRepoInterface

    @Inject
    lateinit var fakeDataGenerator: FakeDataGenerator

    @Before
    fun setUp() {
        DaggerTestAppComponent.builder()
                .build()
                .inject(this)

        imagesRepository = ImagesRepository(flickrApi)
    }

    @Test
    fun `getImages returns list of images`() {
        whenever(flickrApi.getImages()).thenReturn(Single.just(fakeDataGenerator.generateImagesApiResponse()))

        imagesRepository.getImages()
                .test()
                .assertValue(fakeDataGenerator.listOfImages)

        verify(flickrApi).getImages()
    }
}