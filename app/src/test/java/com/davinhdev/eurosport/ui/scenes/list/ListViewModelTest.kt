package com.davinhdev.eurosport.ui.scenes.list

import com.davinhdev.eurosport.domain.interactor.GetData
import com.davinhdev.eurosport.ui.mock.StoryMock
import com.davinhdev.eurosport.ui.mock.VideoMock
import com.davinhdev.eurosport.ui.scenes.BaseViewModelTest
import com.davinhdev.eurosport.ui.scenes.base.UiState
import com.davinhdev.eurosport.ui.scenes.observe
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ListViewModelTest : BaseViewModelTest<ListViewModel>() {

    private val getData = mockk<GetData>()

    override fun createViewModel(): ListViewModel = ListViewModel(
        getData = getData,
        appDispatchers = dispatchersRule.dispatchers,
    )

    @Test
    fun `should update state to error when GetData throw on action LoadData`() = runTest {
        // Given
        val error = Exception("error")
        coEvery { getData() } throws error

        // When
        val viewModel = createViewModel()
        val resultState = viewModel.state.observe(this)

        // Then
        advanceUntilIdle()
        assertThat(resultState.values)
            .containsExactly(
                viewModel.initialState,
                UiState.Error(error)
            )
            .inOrder()

        resultState.finish()
    }

    @Test
    fun `should update state to success when GetData succeed on action LoadData`() = runTest {
        // Given
        val data = listOf(
            VideoMock.item,
            StoryMock.item
        )

        coEvery { getData() } returns data

        val viewModel = createViewModel()

        // When
        val resultState = viewModel.state.observe(this)

        // Then
        advanceUntilIdle()
        assertThat(resultState.values)
            .containsExactly(
                viewModel.initialState,
                UiState.Success(
                    ListViewModel.State(
                        items = data
                    )
                )
            )
            .inOrder()

        resultState.finish()
    }
}
