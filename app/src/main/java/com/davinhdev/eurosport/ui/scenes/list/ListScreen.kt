package com.davinhdev.eurosport.ui.scenes.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.davinhdev.eurosport.domain.model.Story
import com.davinhdev.eurosport.ui.scenes.base.watchAsState
import com.davinhdev.eurosport.ui.scenes.components.UiStateRenderer
import com.davinhdev.eurosport.ui.scenes.list.components.ListSuccess

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    onGoToStoryDetails: (story: Story) -> Unit,
    onDisplayVideo: (url: String) -> Unit,
) {
    val state by viewModel.watchAsState()

    UiStateRenderer(
        uiState = state,
        success = { successState ->
            ListSuccess(
                items = successState.items,
                onGoToStoryDetails = onGoToStoryDetails,
                onDisplayVideo = onDisplayVideo
            )
        },
    )
}