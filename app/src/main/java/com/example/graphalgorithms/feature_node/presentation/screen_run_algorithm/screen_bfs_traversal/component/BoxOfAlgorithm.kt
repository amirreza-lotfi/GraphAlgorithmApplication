package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxOfAlgorithm(
    modifier:Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
    content: @Composable () -> Unit
) {
    Box(modifier = modifier){
        content()
    }
}