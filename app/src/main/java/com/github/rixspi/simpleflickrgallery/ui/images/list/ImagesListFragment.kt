package com.github.rixspi.simpleflickrgallery.ui.images.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.transition.Explode
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.FragmentImagesListBinding
import com.github.rixspi.simpleflickrgallery.di.images.list.ImagesListModule
import com.github.rixspi.simpleflickrgallery.mvibase.MviIntent
import com.github.rixspi.simpleflickrgallery.mvibase.MviView
import com.github.rixspi.simpleflickrgallery.mvibase.MviViewModel
import com.github.rixspi.simpleflickrgallery.mvibase.MviViewState
import com.github.rixspi.simpleflickrgallery.ui.base.BaseFragment
import com.github.rixspi.simpleflickrgallery.ui.base.view.DefaultFragmentTransition
import com.github.rixspi.simpleflickrgallery.ui.images.details.ImageDetailsFragment
import com.github.rixspi.simpleflickrgallery.ui.images.list.adapter.ImageItemType
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.ImagesIntent
import com.github.rixspi.simpleflickrgallery.ui.images.list.mvi.ImagesViewState
import com.github.rixspi.simpleflickrgallery.ui.images.model.UiImage
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ImagesListFragment : BaseFragment(), MviView<ImagesIntent, ImagesViewState> {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: ImagesListViewModel

    private val refreshIntentPublisher = PublishSubject.create<ImagesIntent.RefreshIntent>()

    private lateinit var b: FragmentImagesListBinding

    private fun navigateToDetails(image: UiImage, view: View? = null) {
        val details = ImageDetailsFragment.newInstance()

        var transName: String? = null
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            details.sharedElementEnterTransition = DefaultFragmentTransition()
            exitTransition = Explode()
            transName = view?.transitionName
        }

        details.arguments = Bundle().apply {
            putString(ImageDetailsFragment.EXTRA_IMAGE_ID, image.id)
            putString(ImageDetailsFragment.EXTRA_IMAGE_URL, image.url)
            transName?.let {
                putString(ImageDetailsFragment.EXTRA_IMAGE_TRANS_NAME, it)
            }
        }

        fragmentManager?.beginTransaction()
                ?.apply { transName.let { addSharedElement(view, it) } }
                ?.replace(R.id.content, details)
                ?.addToBackStack(null)
                ?.commit()
    }

    private val adapter: LastAdapter by lazy {
        LastAdapter(vm.items, BR.item)
                .map(
                        UiImage::class.java,
                        ImageItemType { image, view ->
                            navigateToDetails(image, view)
                        }
                )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_images_list, null, false)
        return b.root
    }


    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        dependencyInjection()
        initializeBinding()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        // conflicting with the initial intent but needed when coming back from the
        // AddEditTask activity to refresh the list.
        //refreshIntentPublisher.onNext(ImagesIntent.RefreshIntent(false))
    }

    private fun dependencyInjection() {
        appComponent
                .plus(ImagesListModule(this))
                .inject(this)

        vm = ViewModelProviders.of(this, viewModelFactory).get(ImagesListViewModel::class.java)
    }

    private fun initializeBinding() {
        if (::vm.isInitialized) {
            b.model = vm
            bind()
        } else {
            throw IllegalStateException("Has to be called AFTER view model injection")
        }
    }

    private fun setupRecyclerView() {
        with(b.rvImages) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = this@ImagesListFragment.adapter
            setItemViewCacheSize(30)
        }
    }

    /**
     * Connect the [MviView] with the [MviViewModel]
     * We subscribe to the [MviViewModel] before passing it the [MviView]'s [MviIntent]s.
     * If we were to pass [MviIntent]s to the [MviViewModel] before listening to it,
     * emitted [MviViewState]s could be lost
     */
    private fun bind() {
        // Subscribe to the ViewModel and call render for every emitted state
        vm.states()
                .subscribe {
                    render(it)
                    vm.handleAndroidDataBinding(it)
                }
                .let { registerDisposable(it) }
        // Pass the UI's intents to the ViewModel
        vm.processIntents(intents())
    }

    override fun intents(): Observable<ImagesIntent> {
        return Observable.merge(
                initialIntent(),
                refreshIntent()
        )
    }

    override fun render(state: ImagesViewState) {
        //render android related views e.g: toasts, snackbar, everything we cannot do from viewmodel
    }

    /**
     * The initial Intent the [MviView] emit to convey to the [MviViewModel]
     * that it is ready to receive data.
     * This initial Intent is also used to pass any parameters the [MviViewModel] might need
     * to render the initial [MviViewState] (e.g. the task id to load).
     */
    private fun initialIntent(): Observable<ImagesIntent> = Observable.just(ImagesIntent.InitialIntent)

    private fun refreshIntent(): Observable<ImagesIntent.RefreshIntent> {
        return RxSwipeRefreshLayout.refreshes(b.refreshLayout)
                .map { ImagesIntent.RefreshIntent(true) }
                .mergeWith(refreshIntentPublisher)
    }

    override fun onDestroy() {
        super.onDestroy()

        clearDisposables()
    }
}