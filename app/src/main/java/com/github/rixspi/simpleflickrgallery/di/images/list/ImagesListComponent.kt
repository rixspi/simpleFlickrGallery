package com.github.rixspi.simpleflickrgallery.di.images.list

import com.github.rixspi.simpleflickrgallery.di.base.scope.ActivityScope
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListFragment
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [
    (ImagesListModule::class)
])
interface ImagesListComponent {
    fun inject(fragment: ImagesListFragment): ImagesListFragment
}