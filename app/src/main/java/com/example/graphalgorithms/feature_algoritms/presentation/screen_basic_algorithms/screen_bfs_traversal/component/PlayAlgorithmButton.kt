package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayAlgorithmsButton(
    modifier: Modifier,
    isPlayButtonVisible:Boolean,
    onClick:()->Unit
) {
    if (isPlayButtonVisible) {
        Button(
            onClick = {
                onClick()
            },
            modifier = modifier
        ) {
            Text("Play")
        }
    }
}