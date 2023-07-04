package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkStory
import com.davinhdev.eurosport.domain.model.Story
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

internal fun NetworkStory.toStory(): Story {
    return Story(
        id = id,
        title = title,
        image = image,
        author = author,
        date = Date(TimeUnit.SECONDS.toMillis(date.roundToLong())),
        teaser = teaser,
        sport = sport.toSport()
    )
}