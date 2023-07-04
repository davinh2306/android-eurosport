package com.davinhdev.eurosport.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NetworkResponse(
    @SerialName("videos")
    val videos: List<NetworkVideo>,
    @SerialName("stories")
    val stories: List<NetworkStory>,
)