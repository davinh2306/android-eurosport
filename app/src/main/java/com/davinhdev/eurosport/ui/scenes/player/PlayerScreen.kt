package com.davinhdev.eurosport.ui.scenes.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.davinhdev.eurosport.ui.extension.getActivity
import com.davinhdev.eurosport.ui.scenes.player.components.PlayerHorizontal
import com.davinhdev.eurosport.ui.theme.EurosportColors

@Composable
fun PlayerScreen(
    url: String,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)

    var leavePlayer by remember { mutableStateOf(false) }
    var isReadyToPlay by remember { mutableStateOf(false) }

    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build()
            .apply {
                setMediaItem(MediaItem.fromUri(url))
                prepare()
                playWhenReady = true
            }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, lifecycleEvent ->
            if (lifecycleEvent == Lifecycle.Event.ON_STOP) {
                exoPlayer.release()
            }
        }
        val lifecycle = lifecycleOwner.lifecycle
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    context.getActivity()?.let { currentActivity ->
        PlayerHorizontal(
            activity = currentActivity,
            leavePlayer = leavePlayer,
            onBackPressed = {
                leavePlayer = true
                onBack()
            },
            onOrientationReady = {
                isReadyToPlay = true
            }
        )
    }

    if (isReadyToPlay) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(EurosportColors.black),
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                }
            }
        )
    }
}

