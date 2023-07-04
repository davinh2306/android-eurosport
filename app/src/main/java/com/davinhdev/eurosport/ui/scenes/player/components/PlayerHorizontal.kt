package com.davinhdev.eurosport.ui.scenes.player.components

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import timber.log.Timber

@Composable
fun PlayerHorizontal(
    activity: AppCompatActivity,
    leavePlayer: Boolean,
    onBackPressed: () -> Unit,
    onOrientationReady: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    var navBarBehavior by remember {
        mutableStateOf(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
    }

    BackHandler {
        onBackPressed()
    }

    LaunchedEffect(activity.requestedOrientation) {
        if (!leavePlayer && activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE) {
            onOrientationReady()
        }
    }

    LaunchedEffect(leavePlayer) {
        activity.requestedOrientation = if (leavePlayer) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        }

        Timber.d("SystemBar $leavePlayer")

        systemUiController.isSystemBarsVisible = leavePlayer

        WindowCompat.getInsetsController(
            activity.window,
            activity.window.decorView
        ).also { windowInsetsController ->
            if (!leavePlayer) {
                navBarBehavior = windowInsetsController.systemBarsBehavior
            }
            windowInsetsController.systemBarsBehavior = if (leavePlayer) {
                navBarBehavior
            } else {
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}