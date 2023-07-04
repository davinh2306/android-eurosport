package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkVideo
import com.davinhdev.eurosport.domain.model.Video
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

internal fun NetworkVideo.toVideo(): Video {
    return Video(
        id = id,
        title = title,
        thumbnail = thumbnail,
        url = url,
        date = Date(TimeUnit.SECONDS.toMillis(date.roundToLong())),
        views = views,
        sport = sport.toSport()
    )
}