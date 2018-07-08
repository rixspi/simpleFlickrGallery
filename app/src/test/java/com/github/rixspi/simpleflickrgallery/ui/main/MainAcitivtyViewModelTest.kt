package com.github.rixspi.simpleflickrgallery.ui.main

import android.media.Image
import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.main.mvi.ImagesActionProcessorHolder
import com.github.rixspi.simpleflickrgallery.ui.main.mvi.ImagesIntent
import com.github.rixspi.simpleflickrgallery.ui.main.mvi.ImagesViewState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import org.mockito.Mockito.`when` as _when


class MainAcitivtyViewModelTest : BaseTest() {
    @Inject
    lateinit var imagesRepository: ImagesRepoInterface
    @Inject
    lateinit var fakeDataGenerator: FakeDataGenerator

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var testObserver: TestObserver<ImagesViewState>
    private lateinit var images: List<Image>

    @Before
    fun setUp() {
        DaggerTestAppComponent.builder()
                .build()
                .inject(this)

        viewModel = MainActivityViewModel(
                ImagesActionProcessorHolder(
                        imagesRepository
                )
        )

        testObserver = viewModel
                .states()
                .test()
    }

    @Test
    fun `get all images and load into view`() {
        _when(imagesRepository.getImages()).thenReturn(Single.just(fakeDataGenerator.listOfImages))

        viewModel.processIntents(Observable.just(ImagesIntent.InitialIntent))

        testObserver.assertValueAt(1, ImagesViewState::isLoading)
        testObserver.assertValueAt(2) { imagesViewState -> !imagesViewState.isLoading }
    }

    @Test
    fun `error while loading tasks shows error`() {
        _when(imagesRepository.getImages()).thenReturn(Single.error(Exception()))

        viewModel.processIntents(Observable.just(ImagesIntent.InitialIntent))
        testObserver.assertValueAt(2) { state -> state.error != null }
    }
}