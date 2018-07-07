package com.github.rixspi.simpleflickrgallery.di.base

import com.github.rixspi.simpleflickrgallery.di.main.MainComponent
import com.github.rixspi.simpleflickrgallery.di.main.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (AppSettingsModule::class),
    (NetModule::class),
    (ViewModelModule::class)])
interface AppComponent {

    fun plus(module: MainModule): MainComponent
}