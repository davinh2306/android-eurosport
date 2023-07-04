package com.davinhdev.eurosport.ui.extension

import android.text.format.DateUtils
import com.davinhdev.eurosport.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

internal var dayMonthFormat = "dd MMMM"
internal var shortMonthYearFormat = "dd MMM yyyy"

fun Date.toTimeSpentFormat(): Pair<Int, Int> {
    val calendarNow = Calendar.getInstance().getSimplifiedDate()
    val calendarTarget = Calendar.getInstance()
        .apply { timeInMillis = this@toTimeSpentFormat.time }
        .getSimplifiedDate()

    val diff = Calendar.getInstance().timeInMillis - this.time
    val diffSimplified = calendarNow.timeInMillis - calendarTarget.timeInMillis

    return when {
        !this.isThisWeek() -> {
            val quantity = TimeUnit.MILLISECONDS.toDays(diffSimplified).toInt() / 7
            val result = if (quantity > 0) quantity else 1

            Pair(R.plurals.more_one_week, result)
        }

        !this.isToday() -> {
            val quantity = TimeUnit.MILLISECONDS.toDays(diffSimplified).toInt()
            val result = if (quantity > 0) quantity else 1

            Pair(R.plurals.more_one_day, result)
        }

        !this.isThisHour() -> {
            val quantity = TimeUnit.MILLISECONDS.toHours(diff).toInt()
            val result = if (quantity > 0) quantity else 1

            Pair(R.plurals.less_one_day, result)
        }

        else -> {
            val quantity = TimeUnit.MILLISECONDS.toMinutes(diff).toInt()
            val result = if (quantity > 0) quantity else 1

            Pair(R.plurals.less_one_hour, result)
        }
    }
}

private fun Calendar.getSimplifiedDate() = this.apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Date.toStringFormat(): String {
    val builder = StringBuilder()


    val currentYear = Calendar.getInstance().run {
        get(Calendar.YEAR)
    }

    val dateCalendar = Calendar.getInstance()
    dateCalendar.time = this

    if (currentYear == dateCalendar.get(Calendar.YEAR)) {
        builder.append(
            SimpleDateFormat(dayMonthFormat, Locale.getDefault()).format(this)
        )
    } else {
        builder.append(
            SimpleDateFormat(shortMonthYearFormat, Locale.getDefault()).format(this)
        )
    }

    return builder.toString().split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
    }
}

fun Date.isThisMonth(): Boolean {
    val now = Date()
    val datePlusOneMonth = Date(this.time + 4.times(DateUtils.WEEK_IN_MILLIS))
    return now.before(datePlusOneMonth)
}

fun Date.isThisWeek(): Boolean {
    val now = Date()
    val datePlusOneWeek = Date(this.time + DateUtils.WEEK_IN_MILLIS)
    return now.before(datePlusOneWeek)
}

fun Date.isThisHour(): Boolean {
    val now = Date()
    val datePlusOneHour = Date(time + DateUtils.HOUR_IN_MILLIS)
    return now.before(datePlusOneHour)
}

fun Date.isToday(): Boolean {
    return DateUtils.isToday(time)
}