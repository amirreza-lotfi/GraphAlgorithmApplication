package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ActionButtonOfGraph(
    modifier: Modifier,
    text:String,
    buttonColor: Color,
    isButtonVisible:Boolean,
    onClick:()->Unit
) {
    if(isButtonVisible) {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            onClick = {
                onClick()
            },
        ) {
            Text(text = text, color = Color.White)
        }
    }
}