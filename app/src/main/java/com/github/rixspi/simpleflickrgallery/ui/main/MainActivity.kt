package com.github.rixspi.simpleflickrgallery.ui.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.ActivityMainBinding
import com.github.rixspi.simpleflickrgallery.ui.base.BaseActivity
import com.github.rixspi.simpleflickrgallery.ui.images.list.ImagesListFragment


class MainActivity : BaseActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        loadImagesFragment()
    }

    private fun initializeBinding() {
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun loadImagesFragment() {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.content, ImagesListFragment())
                .commit()
    }
}