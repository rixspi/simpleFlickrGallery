package com.github.rixspi.simpleflickrgallery.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.github.rixspi.simpleflickrgallery.SimpleFlickrGaleryApp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), CompositeDisposablesHandler {
    private val disposables = CompositeDisposable()

    override fun registerDisposable(disposable: Disposable) = disposables.add(disposable)
    
    override fun clearDisposables() = disposables.clear()

    val appComponent by lazy {
        SimpleFlickrGaleryApp.get(applicationContext).appComponent
    }
}