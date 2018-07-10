package com.github.rixspi.simpleflickrgallery.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.github.nitrico.lastadapter.BR
import com.github.nitrico.lastadapter.LastAdapter
import com.github.rixspi.simpleflickrgallery.R
import com.github.rixspi.simpleflickrgallery.databinding.ActivityMainBinding
import com.github.rixspi.simpleflickrgallery.di.main.MainModule
import com.github.rixspi.simpleflickrgallery.mvibase.MviView
import com.github.rixspi.simpleflickrgallery.repository.images.model.Image
import com.github.rixspi.simpleflickrgallery.ui.base.BaseActivity
import com.github.rixspi.simpleflickrgallery.ui.main.mvi.ImagesIntent
import com.github.rixspi.simpleflickrgallery.ui.main.mvi.ImagesViewState
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class MainActivity : BaseActivity(), MviView<ImagesIntent, ImagesViewState> {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: MainActivityViewModel
    private val refreshIntentPublisher = PublishSubject.create<ImagesIntent.RefreshIntent>()

    private lateinit var b: ActivityMainBinding


    val adapter: LastAdapter by lazy {
        LastAdapter(vm.items, BR.item)
                .map<Image>(R.layout.vh_image)
                .into(b.rvImages)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyInjection()
        initializeBinding()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        // conflicting with the initial intent but needed when coming back from the
        // AddEditTask activity to refresh the list.
        refreshIntentPublisher.onNext(ImagesIntent.RefreshIntent(false))
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
            bind()
        } else {
            throw IllegalStateException("Has to be called AFTER view model injection")
        }
    }

    private fun setupRecyclerView() {
        with(b.rvImages) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        adapter
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

//        disposables.add(
//                listAdapter.taskClickObservable.subscribe { task -> showTaskDetailsUi(task.id) })
    }

    override fun intents(): Observable<ImagesIntent> {
        return Observable.merge(
                initialIntent(),
                refreshIntent()
        )
    }

    override fun render(state: ImagesViewState) {

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