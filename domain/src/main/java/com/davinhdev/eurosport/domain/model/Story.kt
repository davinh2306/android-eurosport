package com.davinhdev.eurosport.domain.model

import java.util.Date

data class Story(
    val id: Long,
    val title: String,
    val teaser: String,
    val image: String,
    val author: String,
    val date: Date,
    val sport: Sport,
)
