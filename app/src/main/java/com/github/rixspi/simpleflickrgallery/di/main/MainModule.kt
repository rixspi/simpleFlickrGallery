package com.github.rixspi.simpleflickrgallery.di.main

import com.github.rixspi.simpleflickrgallery.di.base.scope.ActivityScope
import com.github.rixspi.simpleflickrgallery.ui.main.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {
    @Provides
    @ActivityScope
    fun provideMainActivity(): MainActivity = activity
}