package com.github.rixspi.simpleflickrgallery.ui.base

import android.app.Fragment
import com.github.rixspi.simpleflickrgallery.SimpleFlickrGaleryApp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseFragment : Fragment(), CompositeDisposablesHandler {
    private val disposables = CompositeDisposable()

    override fun registerDisposable(disposable: Disposable) = disposables.add(disposable)

    override fun clearDisposables() = disposables.clear()

    val appComponent by lazy {
        SimpleFlickrGaleryApp.get(activity).appComponent
    }
}