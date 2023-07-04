package com.davinhdev.eurosport.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NetworkSport(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("name")
    val name: String = "",
)