package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.ui.theme.black
import com.example.graphalgorithms.feature_node.presentation.ui.theme.lightGray
import com.example.graphalgorithms.feature_node.presentation.ui.theme.teal
import kotlin.math.roundToInt

@Composable
fun WeightPresentation(
    edge: Edge,
    xPositionOfEdgeWeight:Float,
    yPositionOfEdgeWeight:Float,
    textColor: Color = black,
    borderColor: Color = black
) {
    Text(
        text = "  ${edge.weight}  ",
        Modifier
            .offset {
                IntOffset(
                    xPositionOfEdgeWeight.roundToInt(),
                    yPositionOfEdgeWeight.roundToInt()
                )
            }
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        color = textColor,
        fontSize = 14.sp
    )
}