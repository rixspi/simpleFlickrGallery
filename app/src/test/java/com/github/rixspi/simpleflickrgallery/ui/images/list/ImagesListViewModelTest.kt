package com.github.rixspi.simpleflickrgallery.ui.images.list

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.ImagesActionProcessorHolder
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.ImagesIntent
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.ImagesViewState
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import org.mockito.Mockito.`when` as _when


class ImagesListViewModelTest : BaseTest() {
    @Inject
    lateinit var imagesRepository: ImagesRepoInterface
    @Inject
    lateinit var fakeDataGenerator: FakeDataGenerator
    @Inject
    lateinit var imagesMapper: RepoImageMapper

    private lateinit var viewModel: ImagesListViewModel
    private lateinit var testObserver: TestObserver<ImagesViewState>

    @Before
    fun setUp() {
        DaggerTestAppComponent.builder()
                .build()
                .inject(this)

        viewModel = ImagesListViewModel(
                ImagesActionProcessorHolder(imagesRepository, imagesMapper)
        )

        testObserver = viewModel
                .states()
                .test()
    }

    @Test
    fun `get all images and load into view`() {
        _when(imagesRepository.getImages(Mockito.anyBoolean())).thenReturn(Single.just(fakeDataGenerator.listOfImages))

        viewModel.processIntents(Observable.just(ImagesIntent.InitialIntent))

        testObserver.assertValueAt(1, ImagesViewState::isLoading)
        testObserver.assertValueAt(2) { imagesViewState ->
            !imagesViewState.isLoading &&
                    imagesViewState.images[3].id == fakeDataGenerator.listOfImages[3].id()
        }
    }

    @Test
    fun `error changes view state to error`() {
        _when(imagesRepository.getImages(Mockito.anyBoolean())).thenReturn(Single.error(Exception()))

        viewModel.processIntents(Observable.just(ImagesIntent.InitialIntent))
        testObserver.assertValueAt(2) { state -> state.error != null }
    }
}