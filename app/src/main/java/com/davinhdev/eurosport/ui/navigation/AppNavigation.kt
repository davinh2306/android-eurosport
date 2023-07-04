package com.davinhdev.eurosport.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.davinhdev.eurosport.ui.scenes.detail.addDetail
import com.davinhdev.eurosport.ui.scenes.list.ListRoute
import com.davinhdev.eurosport.ui.scenes.list.addList
import com.davinhdev.eurosport.ui.scenes.player.addPlayer
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.squareup.moshi.Moshi
import org.koin.compose.koinInject

const val NAVIGATION_ROOT = "app_root"

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    moshi: Moshi = koinInject(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = NAVIGATION_ROOT,
            startDestination = ListRoute.createRoute(NAVIGATION_ROOT),
        ) {
            addList(navController, NAVIGATION_ROOT, moshi)
            addPlayer(navController, NAVIGATION_ROOT)
            addDetail(navController, NAVIGATION_ROOT, moshi)
        }
    }
}
