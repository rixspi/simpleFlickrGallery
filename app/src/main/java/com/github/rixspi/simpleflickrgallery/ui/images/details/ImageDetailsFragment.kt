package com.github.rixspi.simpleflickrgallery.ui.images.details

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.transition.Fade
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.FragmentImagesDetailsBinding
import com.github.rixspi.simpleflickrgallery.di.images.details.ImageDetailsModule
import com.github.rixspi.simpleflickrgallery.ui.base.BaseFragment
import com.github.rixspi.simpleflickrgallery.utils.view.loadImageThumbnail
import com.github.rixspi.simpleflickrgallery.utils.view.setImageWhenDownloaded
import javax.inject.Inject


class ImageDetailsFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: ImageDetailsViewModel

    private lateinit var b: FragmentImagesDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_images_details,
                null,
                false
        )

        ViewCompat.setTransitionName(b.ivImage, arguments?.getString(EXTRA_IMAGE_TRANS_NAME))
        return b.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        enterTransition = Fade()
        exitTransition = Fade()
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        dependencyInjection()
    }


    private fun dependencyInjection() {
        appComponent
                .plus(ImageDetailsModule(this))
                .inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory).get(ImageDetailsViewModel::class.java)
    }

    companion object {
        const val EXTRA_IMAGE_ID = "img_id_2412437"
        const val EXTRA_IMAGE_TRANS_NAME = "transition_name_22314"

        fun newInstance() = ImageDetailsFragment()
    }

}