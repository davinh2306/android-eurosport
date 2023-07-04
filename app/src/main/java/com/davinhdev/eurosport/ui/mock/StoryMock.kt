package com.davinhdev.eurosport.ui.mock

import com.davinhdev.eurosport.domain.model.Item
import com.davinhdev.eurosport.domain.model.Sport
import com.davinhdev.eurosport.domain.model.Story
import java.util.Date

object StoryMock {
    val item = Item.StoryItem(
        id = 2,
        story = Story(
            id = 2,
            title = "Is Roger Federer The Greatest of All Time?",
            teaser = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            image = "",
            date = Date(),
            author = "David Dinh",
            sport = Sport(
                id = 2,
                name = "Tennis"
            )
        )
    )
}