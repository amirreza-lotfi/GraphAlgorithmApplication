package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.graphalgorithms.feature_node.presentation.ui.theme.white

@Composable
fun ActionButtonOfGraph(
    modifier: Modifier,
    text:String,
    buttonColor: Color,
    isButtonVisible:Boolean,
    onClick:()->Unit
) {

    Button(
        modifier = modifier,
        enabled = isButtonVisible,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        onClick = {
            onClick()
        },
    ) {
        Text(text = text, color = white)
    }
}