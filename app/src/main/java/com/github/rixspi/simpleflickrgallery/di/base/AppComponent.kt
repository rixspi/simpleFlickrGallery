package com.github.rixspi.simpleflickrgallery.di.base

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (AppSettingsModule::class), (NetModule::class)])
interface AppComponent {

}