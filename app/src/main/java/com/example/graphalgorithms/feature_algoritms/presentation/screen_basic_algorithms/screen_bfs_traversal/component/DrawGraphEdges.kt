package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge

@Composable
fun DrawGraphEdges(edgeList:List<Edge>) {

    for (edge: Edge in edgeList) {

        val offsetFromNode = Offset(
            edge.nodeFrom.xNodePosition + 90.dp.value,
            edge.nodeFrom.yNodePosition + 90.dp.value
        )

        val offsetToNode = Offset(
            edge.nodeTo.xNodePosition + 90.dp.value,
            edge.nodeTo.yNodePosition + 90.dp.value
        )

        DrawSingleEdge(offsetFromNode,offsetToNode, Color.Black)
    }
}