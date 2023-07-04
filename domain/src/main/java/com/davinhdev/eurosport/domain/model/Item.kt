package com.davinhdev.eurosport.domain.model

sealed interface Item {
    val id: Long

    data class VideoItem(
        override val id: Long,
        val video: Video,
    ) : Item

    data class StoryItem(
        override val id: Long,
        val story: Story,
    ) : Item
}
