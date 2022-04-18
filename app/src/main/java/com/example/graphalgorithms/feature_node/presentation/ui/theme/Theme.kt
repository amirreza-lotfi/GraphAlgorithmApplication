package com.example.graphalgorithms.feature_node.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.graphalgorithms.ui.theme.Shapes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
    primary = teal,
    background = white,
    secondary = lightTeal,
    secondaryVariant = teal,
    error = red,
)

@Composable
fun GraphAlgorithmsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = white
    )

    if(darkTheme) {
        MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }else{
        MaterialTheme(
            colors = LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun LandingPageTheme(
    content: @Composable() () -> Unit
){
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = teal
    )

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}