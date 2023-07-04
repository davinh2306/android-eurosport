package com.davinhdev.eurosport.ui.scenes.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.davinhdev.eurosport.ui.scenes.base.UiState

@Composable
fun <T> UiStateRenderer(
    modifier: Modifier = Modifier,
    uiState: UiState<T>,
    idle: @Composable () -> Unit = {},
    loading: @Composable () -> Unit = { Loader() },
    error: @Composable (e: Exception) -> Unit = { Error() },
    success: @Composable (data: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        when (uiState) {
            is UiState.Idle -> idle()
            is UiState.Loading -> loading()
            is UiState.Error -> error(uiState.e)
            is UiState.Success -> success(uiState.data)
        }
    }
}
