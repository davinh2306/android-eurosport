package com.davinhdev.eurosport.domain.model

import java.util.Date

data class Video(
    val id: Long,
    val title: String,
    val thumbnail: String,
    val url: String,
    val date: Date,
    val views: Long,
    val sport: Sport,
)
