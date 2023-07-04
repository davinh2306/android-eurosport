package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkSport
import com.davinhdev.eurosport.data.model.NetworkStory
import com.davinhdev.eurosport.domain.model.Sport
import com.davinhdev.eurosport.domain.model.Story
import com.google.common.truth.Truth.assertThat
import io.mockk.checkUnnecessaryStub
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

class StoryTest {

    @After
    fun tearDown() {
        checkUnnecessaryStub()
        clearAllMocks()
    }

    @Test
    fun `should return story when mapped`() = runTest {
        // Given
        val date = 1588104445.007
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

        // When
        val story = networkStory.toStory()

        // Then
        assertThat(story).isInstanceOf(Story::class.java)
        assertThat(story).isEqualTo(
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
    }
}
