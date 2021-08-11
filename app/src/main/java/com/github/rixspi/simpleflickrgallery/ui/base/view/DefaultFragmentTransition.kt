package com.github.rixspi.simpleflickrgallery.ui.base.view

import android.support.transition.*


class DefaultFragmentTransition: TransitionSet() {

    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
                .addTransition(ChangeTransform())
    }
}