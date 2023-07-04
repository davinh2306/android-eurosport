package com.davinhdev.eurosport.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NetworkVideo(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("title")
    val title: String = "",
    @SerialName("thumb")
    val thumbnail: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("date")
    val date: Double = 0.0,
    @SerialName("views")
    val views: Long = 0L,
    @SerialName("sport")
    val sport: NetworkSport = NetworkSport(),
)