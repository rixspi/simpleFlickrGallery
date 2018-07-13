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
import android.widget.Toast
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.FragmentImagesDetailsBinding
import com.github.rixspi.simpleflickrgallery.di.images.details.ImageDetailsModule
import com.github.rixspi.simpleflickrgallery.mvibase.MviView
import com.github.rixspi.simpleflickrgallery.ui.base.BaseFragment
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsIntent
import com.github.rixspi.simpleflickrgallery.ui.images.details.mvi.ImageDetailsViewState
import com.github.rixspi.simpleflickrgallery.utils.view.loadImageThumbnail
import com.github.rixspi.simpleflickrgallery.utils.view.setImageWhenDownloaded
import io.reactivex.Observable
import javax.inject.Inject


class ImageDetailsFragment : BaseFragment(), MviView<ImageDetailsIntent, ImageDetailsViewState> {
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
        initializeBinding()
        //Without it the transition isn't smooth
        loadImageForTransition()
    }

    private fun dependencyInjection() {
        appComponent
                .plus(ImageDetailsModule(this))
                .inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory).get(ImageDetailsViewModel::class.java)
    }

    private fun initializeBinding() {
        if (::vm.isInitialized) {
            b.model = vm
            bind()
        } else {
            throw IllegalStateException("Has to be called AFTER view model injection")
        }
    }

    private fun loadImageForTransition() {
        loadImageThumbnail(b.ivImage, arguments?.getString(EXTRA_IMAGE_URL))
    }

    private fun bind() {
        vm.states()
                .subscribe {
                    render(it)
                    vm.handleAndroidDataBinding(it)
                }
                .let { registerDisposable(it) }
        vm.processIntents(intents())
    }

    override fun intents(): Observable<ImageDetailsIntent> {
        return initialIntent()
    }

    override fun render(state: ImageDetailsViewState) {
        //render android related views e.g: toasts, snackbar, everything we cannot do from viewmodel
        state.image?.let {
            b.ivImage.setImageWhenDownloaded(it.urlToBiggerImage)
            animateTextShowing()
        }

        state.error?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun animateTextShowing() {
        b.root.postDelayed({
            b.tvAuthor.translationX = -1000f
            b.tvTitle.translationX = 4000f
            b.tvAuthor.visibility = View.VISIBLE
            b.tvTitle.visibility = View.VISIBLE

            b.tvAuthor.animate()
                    .translationX(0f)
                    .start()


            b.tvTitle.animate()
                    .translationX(0f)
                    .start()

        }, 300)
    }

    private fun initialIntent(): Observable<ImageDetailsIntent> =
            Observable.just(ImageDetailsIntent.InitialIntent(arguments?.getString(EXTRA_IMAGE_ID)))


    override fun onDestroy() {
        super.onDestroy()

        clearDisposables()
    }
    
    companion object {
        const val EXTRA_IMAGE_ID = "img_id_22314"
        const val EXTRA_IMAGE_TRANS_NAME = "transition_name_22314"
        const val EXTRA_IMAGE_URL = "img_url_22314"

        fun newInstance() = ImageDetailsFragment()
    }

}