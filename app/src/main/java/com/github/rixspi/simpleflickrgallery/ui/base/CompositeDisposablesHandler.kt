package com.github.rixspi.simpleflickrgallery.ui.base

import io.reactivex.disposables.Disposable


interface CompositeDisposablesHandler {
    fun registerDisposable(disposable: Disposable): Boolean

    fun clearDisposables()
}