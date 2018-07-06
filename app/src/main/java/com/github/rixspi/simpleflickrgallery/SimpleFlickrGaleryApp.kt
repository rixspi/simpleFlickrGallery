package com.github.rixspi.simpleflickrgallery

import android.app.Application
import android.content.Context
import com.github.rixspi.simpleflickrgallery.di.base.AppComponent
import com.github.rixspi.simpleflickrgallery.di.base.AppModule
import com.github.rixspi.simpleflickrgallery.di.base.DaggerAppComponent


class SimpleFlickrGaleryApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        fun get(context: Context) = context.applicationContext as SimpleFlickrGaleryApp
    }
}