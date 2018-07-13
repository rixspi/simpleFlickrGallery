package com.github.rixspi.simpleflickrgallery.ui.images.details

import com.github.rixspi.simpleflickrgallery.BaseTest
import com.github.rixspi.simpleflickrgallery.FakeDataGenerator
import com.github.rixspi.simpleflickrgallery.di.base.DaggerTestAppComponent
import com.github.rixspi.simpleflickrgallery.repository.images.ImagesRepoInterface
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsActionProcessorHolder
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsViewState
import com.github.rixspi.simpleflickrgallery.ui.images.mapper.RepoImageMapper
import io.reactivex.observers.TestObserver
import org.junit.Before
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
}