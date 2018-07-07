package com.github.rixspi.simpleflickrgallery.di.main

import com.github.rixspi.simpleflickrgallery.di.base.scope.ActivityScope
import com.github.rixspi.simpleflickrgallery.ui.main.MainActivity
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [
    (MainModule::class)
])
interface MainComponent {
    fun inject(mainActivity: MainActivity): MainActivity
}