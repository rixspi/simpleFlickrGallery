package com.github.rixspi.simpleflickrgallery.di.images.list

import com.github.rixspi.simpleflickrgallery.di.base.scope.FragmentScope
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [
    (ImagesListModule::class)
])
interface ImagesListComponent {
    fun inject(fragment: ImagesListFragment): ImagesListFragment
}