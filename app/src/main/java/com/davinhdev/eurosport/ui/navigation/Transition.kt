package com.davinhdev.eurosport.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

object Transition {
    private const val LONG_DURATION_MS = 500

    fun getStackExitTransition(): AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition? =
        {
            null
        }

    fun getStackPopEnterTransition(): AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? =
        {
            null
        }

    val horizontalEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(LONG_DURATION_MS)
            )
        }

    val horizontalPopExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(LONG_DURATION_MS)
            )
        }
}
