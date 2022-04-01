package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


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
