package com.github.rixspi.simpleflickrgallery.di.base


import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.github.rixspi.simpleflickrgallery.di.base.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private var application: Application) {
    @Provides
    fun getContext(): Context = application.applicationContext

    @Provides
    fun getResources(): Resources = application.resources
}