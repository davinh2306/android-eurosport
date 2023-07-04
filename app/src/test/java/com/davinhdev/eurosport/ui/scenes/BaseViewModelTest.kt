package com.davinhdev.eurosport.ui.scenes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davinhdev.eurosport.ui.scenes.base.BaseViewModel
import io.mockk.checkUnnecessaryStub
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Rule

abstract class BaseViewModelTest<T : BaseViewModel<*, *>> {

    @get:Rule
    val dispatchersRule = TestCoroutineRule()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    abstract fun createViewModel(): T

    @After
    fun tearDown() {
        checkUnnecessaryStub()
        clearAllMocks()
    }
}