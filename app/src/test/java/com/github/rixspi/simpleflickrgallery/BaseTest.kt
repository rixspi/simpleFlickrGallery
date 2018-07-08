package com.github.rixspi.simpleflickrgallery

import org.junit.Rule
import org.mockito.Mockito


open class BaseTest {
    @Suppress("unused")
    @get:Rule
    val rxJavaRule: RxJavaTestRule = RxJavaTestRule()


    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}