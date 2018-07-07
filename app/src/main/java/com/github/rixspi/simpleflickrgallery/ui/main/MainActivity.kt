package com.github.rixspi.simpleflickrgallery.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.ActivityMainBinding
import com.github.rixspi.simpleflickrgallery.di.main.MainModule
import com.github.rixspi.simpleflickrgallery.ui.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: MainActivityViewModel

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyInjection()
        initializeBinding()
    }

    private fun dependencyInjection() {
        appComponent
                .plus(MainModule(this))
                .inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    private fun initializeBinding() {
        if (::vm.isInitialized) {
            b = DataBindingUtil.setContentView(this, R.layout.activity_main)
            b.model = vm
        } else {
            throw IllegalStateException("Has to be called AFTER view model injection")
        }
    }

}