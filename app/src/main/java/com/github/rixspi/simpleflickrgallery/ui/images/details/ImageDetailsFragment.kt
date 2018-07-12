package com.github.rixspi.simpleflickrgallery.ui.images.details

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
import com.github.rixspi.simpleflickrgallery.ui.base.BaseFragment
import com.github.rixspi.simpleflickrgallery.utils.view.loadImageThumbnail
import com.github.rixspi.simpleflickrgallery.utils.view.setImageWhenDownloaded


class ImageDetailsFragment : BaseFragment() {
    private lateinit var b: FragmentImagesDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_images_details, null, false)
        ViewCompat.setTransitionName(b.ivImage, arguments?.getString(EXTRA_IMAGE_TRANS_NAME))
        return b.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // postponeEnterTransition()
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

        val biggerSizeUrl = arguments?.getString(EXTRA_IMAGE)?.dropLast(5).plus("b.jpg")
        loadImageThumbnail(b.ivImage, arguments?.getString(EXTRA_IMAGE))
        b.ivImage.setImageWhenDownloaded(biggerSizeUrl)
    }

    companion object {
        const val EXTRA_IMAGE = "extraimage"
        const val EXTRA_IMAGE_TRANS_NAME = "extraimagetransname"
        fun newInstance(url: String, transName: String? = null): ImageDetailsFragment {
            return ImageDetailsFragment().apply {
                arguments = Bundle()
                        .apply { putString(EXTRA_IMAGE, url) }
                        .apply { putString(EXTRA_IMAGE_TRANS_NAME, transName) }
            }
        }
    }

}