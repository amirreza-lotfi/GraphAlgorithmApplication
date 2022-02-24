package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DrawSingleEdge(
    start: Offset,
    end: Offset,
    color: Color,
    stroke:Float = 6f
){
    Canvas(
        modifier = Modifier.padding(0.dp)
    ) {
        drawLine(
            start = start,
            end = end,
            color = color,
            strokeWidth = stroke,
        )
    }
}
