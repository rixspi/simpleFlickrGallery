package com.github.rixspi.simpleflickrgallery.di.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.rixspi.simpleflickrgallery.ui.images.details.ImageDetailsViewModel
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


@Singleton
class ViewModelFactory @Inject constructor(
        private val viewModels: MutableMap<Class<out ViewModel>,
                Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ImagesListViewModel::class)
    internal abstract fun imagesListViewModel(viewModel: ImagesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailsViewModel::class)
    internal abstract fun imageDetailsViewModel(viewModel: ImageDetailsViewModel): ViewModel
}