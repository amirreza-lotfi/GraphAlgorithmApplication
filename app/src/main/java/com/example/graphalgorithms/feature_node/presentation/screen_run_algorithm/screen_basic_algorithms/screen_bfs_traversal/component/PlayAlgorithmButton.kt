package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component


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
    Button(
        onClick = {
            onClick()
        },
        enabled = isPlayButtonVisible,
        modifier = modifier,
    ) {
        Text("Play")
    }
}