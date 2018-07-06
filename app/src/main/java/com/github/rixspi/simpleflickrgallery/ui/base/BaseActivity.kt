package com.github.rixspi.simpleflickrgallery.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.github.rixspi.simpleflickrgallery.SimpleFlickrGaleryApp

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    val appComponent by lazy {
        SimpleFlickrGaleryApp.get(applicationContext).appComponent
    }
}