package com.davinhdev.eurosport.ui.scenes.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davinhdev.eurosport.R
import com.davinhdev.eurosport.domain.model.Item
import com.davinhdev.eurosport.domain.model.Story
import com.davinhdev.eurosport.ui.mock.StoryMock
import com.davinhdev.eurosport.ui.mock.VideoMock
import com.davinhdev.eurosport.ui.scenes.components.CenteredTitleTopAppBar
import com.davinhdev.eurosport.ui.theme.EurosportTheme
import com.google.accompanist.insets.ui.Scaffold

@Composable
fun ListSuccess(
    items: List<Item>,
    onGoToStoryDetails: (story: Story) -> Unit,
    onDisplayVideo: (url: String) -> Unit,
) {
    val listState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenteredTitleTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = stringResource(id = R.string.app_name)
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            state = listState
        ) {
            items(items) { item ->
                when (item) {
                    is Item.VideoItem -> {
                        ContentVideo(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            video = item.video,
                            onClick = {
                                onDisplayVideo(item.video.url)
                            }
                        )
                    }
                    is Item.StoryItem -> {
                        ContentStory(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            story = item.story,
                            onClick = {
                                onGoToStoryDetails(item.story)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewListSuccess() {
    EurosportTheme {
        ListSuccess(
            items = listOf(
                VideoMock.item,
                StoryMock.item,
            ),
            onGoToStoryDetails = {},
            onDisplayVideo = {},
        )
    }
}