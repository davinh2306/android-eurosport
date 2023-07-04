package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkSport
import com.davinhdev.eurosport.data.model.NetworkVideo
import com.davinhdev.eurosport.domain.model.Sport
import com.davinhdev.eurosport.domain.model.Video
import com.google.common.truth.Truth.assertThat
import io.mockk.checkUnnecessaryStub
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

class VideoTest {

    @After
    fun tearDown() {
        checkUnnecessaryStub()
        clearAllMocks()
    }

    @Test
    fun `should return video when mapped`() = runTest {
        // Given
        val date = 1588104445.007
        val networkVideo = NetworkVideo(
            id = 1,
            title = "Unpopular opinion : Mbappé was better than Messi in the last World cup",
            thumbnail = "",
            url = "",
            date = date,
            views = 100,
            sport = NetworkSport(
                id = 1,
                name = "Football"
            )
        )

        // When
        val video = networkVideo.toVideo()

        // Then
        assertThat(video).isInstanceOf(Video::class.java)
        assertThat(video).isEqualTo(
            Video(
                id = 1,
                title = "Unpopular opinion : Mbappé was better than Messi in the last World cup",
                thumbnail = "",
                url = "",
                date = Date(TimeUnit.SECONDS.toMillis(date.roundToLong())),
                views = 100,
                sport = Sport(
                    id = 1,
                    name = "Football"
                )
            )
        )
    }
}
