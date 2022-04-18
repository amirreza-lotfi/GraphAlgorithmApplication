package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleOfAlgorithmScreen(
    modifier: Modifier,
    onBackClicked:()->Unit,
    onDescriptionClicked:()->Unit
) {
    Box(
       modifier = modifier
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "back to Graph screen",
            Modifier.size(24.dp)
                .align(Alignment.TopStart)
                .clickable {
                    onBackClicked()
                }

        )
        Icon(
            Icons.Filled.Description,
            contentDescription = "back to Graph screen",
            Modifier.size(24.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    onDescriptionClicked()
                }
        )
    }
}