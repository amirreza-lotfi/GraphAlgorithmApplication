package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import kotlin.math.roundToInt

@Composable
fun WeightPresentation(
    edge: Edge,
    xPositionOfEdgeWeight:Float,
    yPositionOfEdgeWeight:Float
) {
    Text(text = edge.weight.toString(),
        Modifier
            .offset{
                IntOffset(
                    xPositionOfEdgeWeight.roundToInt(),
                    yPositionOfEdgeWeight.roundToInt()
                )
            }
            .background(lightGray),
        color = Color.Black,
        fontSize = 16.sp)
}