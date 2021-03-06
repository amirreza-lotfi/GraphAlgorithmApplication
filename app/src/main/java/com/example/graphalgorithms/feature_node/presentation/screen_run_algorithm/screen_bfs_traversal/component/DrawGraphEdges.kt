package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.screen_graph.components.WeightPresentation
import com.example.graphalgorithms.feature_node.presentation.ui.theme.black

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

        val xPositionOfEdgeWeight = (edge.nodeFrom.xNodePosition + edge.nodeTo.xNodePosition)/2 + 24
        val yPositionOfEdgeWeight = (edge.nodeFrom.yNodePosition + edge.nodeTo.yNodePosition)/2 + 24

        DrawSingleEdge(offsetFromNode,offsetToNode, black)
        WeightPresentation(
            edge = edge,
            xPositionOfEdgeWeight = xPositionOfEdgeWeight,
            yPositionOfEdgeWeight = yPositionOfEdgeWeight)
    }
}