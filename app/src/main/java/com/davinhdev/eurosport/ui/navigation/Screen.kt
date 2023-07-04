package com.davinhdev.eurosport.ui.navigation

abstract class Screen(
    private val route: String,
    vararg paramKeys: String
) {
    private val paramRoute by lazy {
        "$route${
            if (paramKeys.isNotEmpty()) {
                "?${paramKeys.joinToString("&") { "$it={$it}" }}"
            } else ""
        }"
    }

    fun createRoute(root: String): String {
        return "$root/$paramRoute"
    }
}