package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AlgorithmScreenNavigationButton(
    modifier: Modifier,
    onClick:()->Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
    ) {
        Text(text = "Run Algorithms")
    }
}