package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge


@Composable
fun DrawVisitedEdgeWithRedColor(visitedEdges:List<Edge>) {
    for (edge: Edge in visitedEdges) {

        val offsetFromNode = Offset(
            edge.nodeFrom.xNodePosition + 90.dp.value,
            edge.nodeFrom.yNodePosition + 90.dp.value
        )

        val offsetToNode = Offset(
            edge.nodeTo.xNodePosition + 90.dp.value,
            edge.nodeTo.yNodePosition + 90.dp.value
        )

        DrawSingleEdge(offsetFromNode, offsetToNode, Color.Red, 10f)
    }

}