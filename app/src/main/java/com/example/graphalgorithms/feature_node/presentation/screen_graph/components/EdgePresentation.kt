package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component.DrawSingleEdge
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel
import com.example.graphalgorithms.feature_node.presentation.ui.theme.black

@Composable
fun EdgePresentation(
    screenViewModel: GraphScreenViewModel
){
    val edgeList: SnapshotStateList<Edge> = screenViewModel.edgeList

    key(screenViewModel.redrawEdges.value) {
        for (edge: Edge in edgeList) {
            val offsetFromNode = Offset(
                edge.nodeFrom.xNodePosition + 60.dp.value,
                edge.nodeFrom.yNodePosition + 60.dp.value
            )

            val offsetToNode = Offset(
                edge.nodeTo.xNodePosition + 60.dp.value,
                edge.nodeTo.yNodePosition + 60.dp.value
            )
            DrawSingleEdge(start = offsetFromNode, end = offsetToNode, color = black)

            val xPositionOfEdgeWeight = (edge.nodeFrom.xNodePosition + edge.nodeTo.xNodePosition)/2 + 24
            val yPositionOfEdgeWeight = (edge.nodeFrom.yNodePosition + edge.nodeTo.yNodePosition)/2 + 24


            WeightPresentation(edge,xPositionOfEdgeWeight, yPositionOfEdgeWeight)
        }
    }
}