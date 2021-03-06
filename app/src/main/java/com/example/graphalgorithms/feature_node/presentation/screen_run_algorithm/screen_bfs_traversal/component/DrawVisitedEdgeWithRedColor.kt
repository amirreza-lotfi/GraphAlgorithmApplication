package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.WeightPresentation


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

        DrawSingleEdge(offsetFromNode, offsetToNode, MaterialTheme.colors.error, 10f)

        val xPositionOfEdgeWeight = (edge.nodeFrom.xNodePosition + edge.nodeTo.xNodePosition)/2 + 24
        val yPositionOfEdgeWeight = (edge.nodeFrom.yNodePosition + edge.nodeTo.yNodePosition)/2 + 24


        WeightPresentation(
            edge,
            xPositionOfEdgeWeight,
            yPositionOfEdgeWeight,
            MaterialTheme.colors.error,
            MaterialTheme.colors.error
        )
    }

}