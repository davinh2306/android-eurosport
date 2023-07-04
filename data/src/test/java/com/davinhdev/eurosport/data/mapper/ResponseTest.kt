package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkResponse
import com.davinhdev.eurosport.data.model.NetworkSport
import com.davinhdev.eurosport.data.model.NetworkStory
import com.davinhdev.eurosport.data.model.NetworkVideo
import com.davinhdev.eurosport.domain.model.Response
import com.davinhdev.eurosport.domain.model.Sport
import com.davinhdev.eurosport.domain.model.Story
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

class ResponseTest {

    @After
    fun tearDown() {
        checkUnnecessaryStub()
        clearAllMocks()
    }

    @Test
    fun `should return response when mapped`() = runTest {
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
        val networkStory = NetworkStory(
            id = 1,
            title = "Is Roger Federer The Greatest of All Time?",
            teaser = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            image = "",
            date = date,
            author = "David Dinh",
            sport = NetworkSport(
                id = 1,
                name = "Tennis"
            )
        )

        val networkResponse = NetworkResponse(
            videos = listOf(networkVideo),
            stories = listOf(networkStory)
        )

        // When
        val response = networkResponse.toResponse()

        // Then
        assertThat(response).isInstanceOf(Response::class.java)
        assertThat(response).isEqualTo(
            Response(
                videos = listOf(
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
                ),
                stories = listOf(
                    Story(
                        id = 1,
                        title = "Is Roger Federer The Greatest of All Time?",
                        teaser = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        image = "",
                        date = Date(TimeUnit.SECONDS.toMillis(date.roundToLong())),
                        author = "David Dinh",
                        sport = Sport(
                            id = 1,
                            name = "Tennis"
                        )
                    )
                )
            )
        )
    }
}
