package com.davinhdev.eurosport.ui.scenes.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.davinhdev.eurosport.domain.model.Story
import com.davinhdev.eurosport.ui.navigation.Screen
import com.davinhdev.eurosport.ui.scenes.detail.DetailRoute
import com.davinhdev.eurosport.ui.scenes.player.PlayerRoute
import com.google.accompanist.navigation.animation.composable
import com.squareup.moshi.Moshi
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object ListRoute : Screen("list")

fun NavGraphBuilder.addList(
    navController: NavController,
    root: String,
    moshi: Moshi
) {
    composable(ListRoute.createRoute(root)) {
        ListScreen(
            viewModel = getViewModel(),
            onGoToStoryDetails = { story ->
                val jsonAdapter = moshi.adapter(Story::class.java).lenient()
                val storyJson = jsonAdapter.toJson(story)

                val encodedJson = URLEncoder.encode(storyJson, StandardCharsets.UTF_8.toString())

                navController.navigate(
                    DetailRoute.createRoute(
                        root = root,
                        story = encodedJson,
                    )
                )
            },
            onDisplayVideo = { videoUrl ->
                val encodedUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8.toString())

                navController.navigate(
                    PlayerRoute.createRoute(
                        root = root,
                        url = encodedUrl,
                    )
                )
            }
        )
    }
}
