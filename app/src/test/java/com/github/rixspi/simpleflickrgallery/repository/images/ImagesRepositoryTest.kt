package com.github.rixspi.simpleflickrgallery.repository.images

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.net.FlickrApi
import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import javax.inject.Inject
import org.mockito.Mockito.`when` as _when


class ImagesRepositoryTest : BaseTest() {

    @Inject
    lateinit var flickrApi: FlickrApi

    lateinit var imagesRepository: ImagesRepository

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
    fun `getImages returns a list of images`() {
        _when(flickrApi.getImages()).thenReturn(Single.just(fakeDataGenerator.generateImagesApiResponse()))

        imagesRepository.getImages()
                .test()
                .assertValue(fakeDataGenerator.listOfImages)

        verify(flickrApi).getImages()
    }

    @Test
    fun `getImages caches images in memory`() {
        //When data is cached and cache is not dirty
        _when(flickrApi.getImages()).thenReturn(Single.just(fakeDataGenerator.generateImagesApiResponse()))
        imagesRepository.getImages().subscribe()
        assertFalse(imagesRepository.cacheIsDirty)

        assertEquals(fakeDataGenerator.listOfImages.size, imagesRepository.cachedImages!!.size)
    }

    @Test
    fun `getImages immediately returns cached images if available`() {
        //When data is cached and cache is not dirty
        _when(flickrApi.getImages()).thenReturn(Single.just(fakeDataGenerator.generateImagesApiResponse()))
        imagesRepository.getImages().subscribe()
        assertFalse(imagesRepository.cacheIsDirty)

        //Then return a list of images without calling api
        imagesRepository.getImages(false).subscribe()

        verify(flickrApi, times(1)).getImages()
    }

    @Test
    fun `getImages with update flag fetches the images from api even when cache is not empty`() {
        //When data is cached and cache is not dirty
        _when(flickrApi.getImages()).thenReturn(Single.just(fakeDataGenerator.generateImagesApiResponse()))
        imagesRepository.getImages().subscribe()
        assertFalse(imagesRepository.cacheIsDirty)

        assertTrue(imagesRepository.cachedImages != null)
        //Then return a list of images without calling api
        imagesRepository.getImages(true).subscribe()

        verify(flickrApi, times(2)).getImages()
    }


}