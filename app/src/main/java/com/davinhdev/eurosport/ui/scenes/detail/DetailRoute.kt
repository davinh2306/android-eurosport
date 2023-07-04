package com.davinhdev.eurosport.ui.scenes.detail

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.davinhdev.eurosport.domain.model.Story
import com.davinhdev.eurosport.ui.extension.shareUrl
import com.davinhdev.eurosport.ui.navigation.Screen
import com.davinhdev.eurosport.ui.navigation.Transition
import com.google.accompanist.navigation.animation.composable
import com.squareup.moshi.Moshi
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

private const val NavArgStory: String = "story"

object DetailRoute : Screen("detail/{$NavArgStory}") {
    fun createRoute(
        root: String,
        story: String
    ): String {
        return createRoute(root).replace("{$NavArgStory}", story)
    }
}

fun NavGraphBuilder.addDetail(
    navController: NavController,
    root: String,
    moshi: Moshi
) {
    composable(
        route = DetailRoute.createRoute(root),
        arguments = listOf(
            navArgument(NavArgStory) { type = NavType.StringType },
        ),
        enterTransition = Transition.horizontalEnterTransition,
        popExitTransition = Transition.horizontalPopExitTransition,
        exitTransition = Transition.getStackExitTransition(),
        popEnterTransition = Transition.getStackPopEnterTransition(),
    ) {
        it.arguments?.getString(NavArgStory)?.let { storyJson ->
            val context = LocalContext.current

            val jsonAdapter = moshi.adapter(Story::class.java).lenient()
            val decodedJson = URLDecoder.decode(storyJson, StandardCharsets.UTF_8.toString())
            val story = jsonAdapter.fromJson(decodedJson)

            DetailScreen(
                story = story!!,
                onBack = {
                    navController.popBackStack()
                },
                onShare = {
                    context.shareUrl(story.image, story.title) // should share a deeplink instead
                }
            )
        } ?: navController.popBackStack()
    }
}