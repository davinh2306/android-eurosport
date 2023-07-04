package com.davinhdev.eurosport.ui.scenes.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davinhdev.eurosport.R
import com.davinhdev.eurosport.domain.model.Story
import com.davinhdev.eurosport.ui.extension.isThisMonth
import com.davinhdev.eurosport.ui.extension.toStringFormat
import com.davinhdev.eurosport.ui.extension.toTimeSpentFormat
import com.davinhdev.eurosport.ui.mock.StoryMock
import com.davinhdev.eurosport.ui.scenes.components.Tag
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme

@Composable
fun DetailScreen(
    story: Story,
    onBack: () -> Unit,
    onShare: (url: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EurosportColors.white)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(EurosportColors.darkGrey),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                model = story.image,
                placeholder = painterResource(R.drawable.ic_placeholder),
                fallback = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            0F to EurosportColors.black.copy(alpha = .5f),
                            1F to EurosportColors.black.copy(alpha = .0f)
                        )
                    )
            )

            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopStart),
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = EurosportColors.white
                )
            }

            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopEnd),
                onClick = {
                    onShare(story.image)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = EurosportColors.white
                )
            }
        }

        Tag(
            text = story.sport.name
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            text = story.title,
            style = MaterialTheme.typography.h2,
            color = EurosportColors.black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                    end = 8.dp,
                ),
            text = stringResource(id = R.string.by_author, story.author),
            style = MaterialTheme.typography.subtitle1,
            color = EurosportColors.black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top = 4.dp,
                    end = 8.dp,
                ),
            text = if (story.date.isThisMonth()) {
                val (label, count) = story.date.toTimeSpentFormat()
                pluralStringResource(label, count, count)
            } else {
                story.date.toStringFormat()
            },
            style = MaterialTheme.typography.body1,
            color = EurosportColors.black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
            text = story.teaser,
            style = MaterialTheme.typography.body2,
            color = EurosportColors.black,
        )
    }
}

@Preview
@Composable
private fun PreviewListSuccess() {
    EurosportTheme {
        DetailScreen(
            story = StoryMock.item.story,
            onBack = {},
            onShare = {}
        )
    }
}