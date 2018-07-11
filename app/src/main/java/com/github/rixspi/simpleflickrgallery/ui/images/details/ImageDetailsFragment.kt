package com.github.rixspi.simpleflickrgallery.ui.images.details

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionListenerAdapter
import android.support.transition.TransitionSet
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy.ALL
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.FragmentImagesDetailsBinding
import com.github.rixspi.simpleflickrgallery.di.base.GlideApp
import com.github.rixspi.simpleflickrgallery.ui.base.BaseFragment
import com.github.rixspi.simpleflickrgallery.utils.view.loadImageThumbnail


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


        val biggerSizeUrl = arguments?.getString(EXTRA_IMAGE)?.dropLast(5).plus("b.jpg")
        val full = GlideApp.with(context)
                .load(biggerSizeUrl)
                .diskCacheStrategy(ALL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        b.ivImage.setImageDrawable(resource)
                        return false
                    }

                })
                .preload()

        (sharedElementEnterTransition as TransitionSet).addListener(object : TransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition) {

                super.onTransitionEnd(transition)
            }
        })
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val biggerSizeUrl = arguments?.getString(EXTRA_IMAGE)?.dropLast(5).plus("b.jpg")
        //loadImageThumbnail(b.ivImage,arguments?.getString(EXTRA_IMAGE))

        GlideApp.with(this)
                .load(biggerSizeUrl)
                .downloadOnly(100, 100)


        //b.ivImage.loadImage(arguments?.getString(EXTRA_IMAGE))

        loadImageThumbnail(b.ivImage, arguments?.getString(EXTRA_IMAGE))


        b.ivImage.setOnClickListener {
            //b.ivImage.loadImage(biggerSizeUrl)

        }
//        b.ivImage.postDelayed({
//            b.ivImage.loadImage(biggerSizeUrl)
//        }, 1000)
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