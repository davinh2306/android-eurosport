package com.davinhdev.eurosport.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NetworkStory(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("title")
    val title: String = "",
    @SerialName("teaser")
    val teaser: String = "",
    @SerialName("image")
    val image: String = "",
    @SerialName("author")
    val author: String = "",
    @SerialName("date")
    val date: Double = 0.0,
    @SerialName("sport")
    val sport: NetworkSport = NetworkSport(),
)