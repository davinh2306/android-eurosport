package com.davinhdev.eurosport.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val EurosportLightColors = lightColors(
    primary = EurosportColors.white,
    secondary = EurosportColors.darkBlue,
    secondaryVariant = EurosportColors.lightBlue,
    background = EurosportColors.lightGrey,
    surface = EurosportColors.white,
    onPrimary = EurosportColors.black,
    onSecondary = EurosportColors.white,
    onBackground = EurosportColors.black,
    onSurface = EurosportColors.black,
)

object EurosportColors {
    val white = Color(0xFFFFFFFF)
    val black = Color(0xFF000000)
    val lightGrey = Color(0XFFF2F2F2)
    val darkGrey = Color(0XFF9E9E9E)
    val darkBlue = Color(0XFF141D4A)
    val lightBlue = Color(0XFF80BCF9)
}