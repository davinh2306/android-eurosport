package com.davinhdev.eurosport.ui.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@Composable
fun EurosportTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = EurosportLightColors,
        typography = EurosportTypography,
        shapes = EurosportShapes,
        content = content
    )

    // Set Status and Navigation bar color to match the theme
    LocalView.current.also { view ->
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.apply {
                    decorView.setBackgroundColor(EurosportColors.white.toArgb())
                    statusBarColor = EurosportColors.darkBlue.toArgb()
                    navigationBarColor = EurosportColors.darkBlue.toArgb()
                }
            }
        }
    }
}