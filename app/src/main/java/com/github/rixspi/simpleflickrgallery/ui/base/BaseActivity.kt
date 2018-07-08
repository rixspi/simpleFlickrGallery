package com.github.rixspi.simpleflickrgallery.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.github.rixspi.simpleflickrgallery.SimpleFlickrGaleryApp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    fun registerDisposable(disposable: Disposable) = disposables.add(disposable)

    fun clearDisposables() = disposables.clear()

    val appComponent by lazy {
        SimpleFlickrGaleryApp.get(applicationContext).appComponent
    }
}