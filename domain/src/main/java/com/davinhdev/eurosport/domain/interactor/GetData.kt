package com.davinhdev.eurosport.domain.interactor

import com.davinhdev.eurosport.domain.model.Item
import com.davinhdev.eurosport.domain.repository.EurosportRepository
import org.koin.core.annotation.Factory

@Factory
class GetData(
    private val eurosportRepository: EurosportRepository
) : NoParamsInteractor<List<Item>>() {

    override suspend fun doWork(): List<Item> {
        val response = eurosportRepository.getItems().getOrThrow()

        val videos = response.videos
            .sortedBy { it.date }
            .reversed()
            .map { Item.VideoItem(it.id, it) }
        val stories = response.stories
            .sortedBy { it.date }
            .reversed()
            .map { Item.StoryItem(it.id, it) }

        val remains = when {
            videos.size > stories.size -> videos.drop(response.stories.size)
            videos.size < stories.size -> stories.drop(response.videos.size)
            else -> listOf()
        }

        return videos.zip(stories).flatMap { listOf(it.first, it.second) } + remains
    }
}