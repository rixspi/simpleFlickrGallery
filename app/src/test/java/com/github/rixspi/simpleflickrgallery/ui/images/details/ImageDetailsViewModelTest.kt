package com.github.rixspi.simpleflickrgallery.ui.images.details

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsActionProcessorHolder
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsIntent
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsViewState
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import org.mockito.Mockito.`when` as _when


class ImageDetailsViewModelTest : BaseTest() {
    @Inject
    lateinit var imagesRepository: ImagesRepoInterface
    @Inject
    lateinit var fakeDataGenerator: FakeDataGenerator
    @Inject
    lateinit var imagesMapper: RepoImageMapper

    private lateinit var viewModel: ImageDetailsViewModel
    private lateinit var testObserver: TestObserver<ImageDetailsViewState>

    @Before
    fun setUp() {
        DaggerTestAppComponent.builder()
                .build()
                .inject(this)

        viewModel = ImageDetailsViewModel(
                ImageDetailsActionProcessorHolder(imagesRepository, imagesMapper)
        )

        testObserver = viewModel
                .states()
                .test()
    }

    @Test
    fun `get image info from cache`() {
        val fakeImagesList = fakeDataGenerator.listOfImages
        _when(imagesRepository.getImageFromCache(Mockito.anyString())).thenReturn(Single.just(fakeImagesList[1]))

        viewModel.processIntents(Observable.just(ImageDetailsIntent.InitialIntent(fakeImagesList[1].id())))

        testObserver.assertValueAt(1, ImageDetailsViewState::isLoading)
        testObserver.assertValueAt(2) { imageDetailsViewState ->
            !imageDetailsViewState.isLoading
                    && imageDetailsViewState.image!!.id == fakeImagesList[1].id()
        }
    }

    @Test
    fun `error changes view state to error`() {
        _when(imagesRepository.getImageFromCache(Mockito.anyString())).thenReturn(Single.error(Exception()))

        viewModel.processIntents(Observable.just(ImageDetailsIntent.InitialIntent("notExisting")))
        testObserver.assertValueAt(2) { state -> state.error != null }
    }
}