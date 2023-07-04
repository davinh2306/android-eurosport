package com.davinhdev.eurosport.ui.mock

import com.davinhdev.eurosport.domain.model.Item
import com.davinhdev.eurosport.domain.model.Sport
import com.davinhdev.eurosport.domain.model.Video
import java.util.Date

object VideoMock {
    val item = Item.VideoItem(
        id = 1,
        video = Video(
            id = 1,
            title = "Unpopular opinion : Mbappé was better than Messi in the last World cup",
            thumbnail = "",
            url = "",
            date = Date(),
            views = 100,
            sport = Sport(
                id = 1,
                name = "Football"
            )
        )
    )
}