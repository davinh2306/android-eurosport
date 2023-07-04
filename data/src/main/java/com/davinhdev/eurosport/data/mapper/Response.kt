package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkResponse
import com.davinhdev.eurosport.domain.model.Response

internal fun NetworkResponse.toResponse(): Response {
    return Response(
        videos = videos.map { it.toVideo() },
        stories = stories.map { it.toStory() },
    )
}