package com.davinhdev.eurosport.data.mapper

import com.davinhdev.eurosport.data.model.NetworkSport
import com.davinhdev.eurosport.domain.model.Sport

internal fun NetworkSport.toSport(): Sport {
    return Sport(
        id = id,
        name = name,
    )
}