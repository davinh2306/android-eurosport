package com.davinhdev.eurosport.ui.scenes

import com.davinhdev.eurosport.ui.scenes.base.AppDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestCoroutineRule(
    private val testCoroutineDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {
    val dispatchers = AppDispatchers(testCoroutineDispatcher, testCoroutineDispatcher)

    override fun starting(description: Description) {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}