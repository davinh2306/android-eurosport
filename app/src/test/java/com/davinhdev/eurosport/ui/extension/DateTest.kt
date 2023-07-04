package com.davinhdev.eurosport.ui.extension

import android.text.format.DateUtils
import com.davinhdev.eurosport.R
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.Date

class DateTest {
    @Test
    fun `should return minutes spent when call toTimeSpentFormat() on same day and same hour`() = runTest {
        val now = Date()
        val dateMinusFiveMin = Date(now.time - DateUtils.MINUTE_IN_MILLIS.times(5))

        mockkStatic(Date::isToday)
        every { any<Date>().isToday() } returns true

        val result = dateMinusFiveMin.toTimeSpentFormat()
        Truth.assertThat(result).isEqualTo(Pair(R.plurals.less_one_hour, 5))
    }

    @Test
    fun `should return hours spent when call toTimeSpentFormat() on same day but not same hour`() = runTest {
        val now = Date()
        val dateMinusFourHours = Date(now.time - DateUtils.HOUR_IN_MILLIS.times(4))

        mockkStatic(Date::isToday)
        every { any<Date>().isToday() } returns true

        val result = dateMinusFourHours.toTimeSpentFormat()
        Truth.assertThat(result).isEqualTo(Pair(R.plurals.less_one_day, 4))
    }

    @Test
    fun `should return 2 days spent when call toTimeSpentFormat() on different day with more than 48 hours`() = runTest {
        val now = Date()
        val dateMinusFortyNineHours = Date(now.time - DateUtils.HOUR_IN_MILLIS.times(49))

        mockkStatic(Date::isToday)
        every { any<Date>().isToday() } returns false

        val result = dateMinusFortyNineHours.toTimeSpentFormat()
        Truth.assertThat(result).isEqualTo(Pair(R.plurals.more_one_day, 2))
    }

    @Test
    fun `should return 2 days spent when call toTimeSpentFormat() on different day with less than 48 hours`() = runTest {
        val now = Date()
        val dateMinusFortySevenHours = Date(now.time - DateUtils.HOUR_IN_MILLIS.times(47))

        mockkStatic(Date::isToday)
        every { any<Date>().isToday() } returns false

        val result = dateMinusFortySevenHours.toTimeSpentFormat()
        Truth.assertThat(result).isEqualTo(Pair(R.plurals.more_one_day, 2))
    }

    @Test
    fun `should return 2 weeks spent when call toTimeSpentFormat() on different day with more than 7 days`() = runTest {
        val now = Date()
        val dateMinusFortySevenHours = Date(now.time - DateUtils.HOUR_IN_MILLIS.times(336))

        mockkStatic(Date::isToday)
        every { any<Date>().isToday() } returns false

        val result = dateMinusFortySevenHours.toTimeSpentFormat()
        Truth.assertThat(result).isEqualTo(Pair(R.plurals.more_one_week, 2))
    }
}
