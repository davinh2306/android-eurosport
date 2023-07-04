package com.davinhdev.eurosport.ui.scenes.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.davinhdev.eurosport.domain.model.Video
import com.davinhdev.eurosport.ui.extension.isThisMonth
import com.davinhdev.eurosport.ui.extension.toStringFormat
import com.davinhdev.eurosport.ui.extension.toTimeSpentFormat
import com.davinhdev.eurosport.ui.mock.StoryMock
import com.davinhdev.eurosport.ui.mock.VideoMock
import com.davinhdev.eurosport.ui.scenes.components.Tag
import com.davinhdev.eurosport.ui.theme.EurosportColors
import com.davinhdev.eurosport.ui.theme.EurosportTheme
import java.text.NumberFormat

@Composable
fun ContentStory(
    modifier: Modifier = Modifier,
    story: Story,
    onClick: () -> Unit
) {
    val date = if (story.date.isThisMonth()) {
        val (label, count) = story.date.toTimeSpentFormat()
        pluralStringResource(label, count, count)
    } else {
        story.date.toStringFormat()
    }

    Content(
        modifier = modifier,
        title = story.title,
        subtitle = stringResource(id = R.string.by_author_date, story.author, date),
        tag = story.sport.name,
        image = story.image,
        isVideo = false,
        onClick = onClick
    )
}

@Composable
fun ContentVideo(
    modifier: Modifier = Modifier,
    video: Video,
    onClick: () -> Unit
) {
    Content(
        modifier = modifier,
        title = video.title,
        subtitle = stringResource(id = R.string.number_of_views, NumberFormat.getInstance().format(video.views)),
        tag = video.sport.name,
        image = video.thumbnail,
        isVideo = true,
        onClick = onClick
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    tag: String,
    image: String,
    isVideo: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(EurosportColors.white)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16 / 9f),
                model = image,
                placeholder = painterResource(R.drawable.ic_placeholder),
                fallback = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            if (isVideo) {
                Image(
                    modifier = modifier
                        .size(56.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                )
            }
        }

        Tag(
            text = tag
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            text = title,
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
                    bottom = 16.dp
                ),
            text = subtitle,
            style = MaterialTheme.typography.subtitle1,
            color = EurosportColors.darkGrey,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun PreviewContentVideo() {
    EurosportTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            ContentVideo(
                video = VideoMock.item.video,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewContentStory() {
    EurosportTheme {
        ContentStory(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            story = StoryMock.item.story,
            onClick = {}
        )
    }
}
