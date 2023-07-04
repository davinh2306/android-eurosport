package com.davinhdev.eurosport.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.davinhdev.eurosport.ui.navigation.AppNavigation
import com.davinhdev.eurosport.ui.theme.EurosportTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EurosportTheme {
                val navController = rememberAnimatedNavController()

                AppNavigation(
                    navController = navController,
                    startDestination = "app_root"
                )
            }
        }
    }
}