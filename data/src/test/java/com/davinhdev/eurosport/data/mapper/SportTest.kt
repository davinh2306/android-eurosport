package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkSport
import com.davinhdev.eurosport.domain.model.Sport
import com.google.common.truth.Truth.assertThat
import io.mockk.checkUnnecessaryStub
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class SportTest {

    @After
    fun tearDown() {
        checkUnnecessaryStub()
        clearAllMocks()
    }

    @Test
    fun `should return sport when mapped`() = runTest {
        // Given
        val networkSport = NetworkSport(
            id = 1,
            name = "name"
        )

        // When
        val sport = networkSport.toSport()

        // Then
        assertThat(sport).isInstanceOf(Sport::class.java)
        assertThat(sport).isEqualTo(
            Sport(
                id = 1,
                name = "name"
            )
        )
    }
}
