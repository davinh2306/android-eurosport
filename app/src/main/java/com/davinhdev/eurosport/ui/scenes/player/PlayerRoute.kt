package com.davinhdev.eurosport.ui.scenes.player

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.davinhdev.eurosport.ui.navigation.Screen
import com.google.accompanist.navigation.animation.composable

private const val NavArgUrl: String = "url"

object PlayerRoute : Screen("player/{$NavArgUrl}") {
    fun createRoute(
        root: String,
        url: String
    ): String {
        return createRoute(root).replace("{$NavArgUrl}", url)
    }
}

fun NavGraphBuilder.addPlayer(
    navController: NavController,
    root: String
) {
    composable(
        route = PlayerRoute.createRoute(root),
        arguments = listOf(
            navArgument(NavArgUrl) { type = NavType.StringType },
        ),
    ) {
        it.arguments?.getString(NavArgUrl)?.let { videoUrl ->
            PlayerScreen(
                url = videoUrl,
                onBack = {
                    navController.popBackStack()
                }
            )
        } ?: navController.popBackStack()
    }
}
