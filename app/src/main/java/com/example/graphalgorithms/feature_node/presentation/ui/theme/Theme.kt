package com.example.graphalgorithms.feature_node.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.graphalgorithms.ui.theme.Shapes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = teal,
    primaryVariant = black,
    secondary = orange,
    secondaryVariant = teal,
)

@Composable
fun GraphAlgorithmsTheme(
    content: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = lightGray
    )

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}