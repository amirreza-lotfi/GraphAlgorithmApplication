package com.example.graphalgorithms.feature_node.presentation.screen_graph.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component.DrawSingleEdge
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel

@Composable
fun EdgePresentation(
    viewModel: NodeFeatureViewModel
){
    val edgeList: SnapshotStateList<Edge> = viewModel.edgeList

    if(viewModel.redrawEdges.value!=0) {
        for (edge: Edge in edgeList) {
            val offsetFromNode = Offset(
                edge.nodeFrom.xNodePosition + 90.dp.value,
                edge.nodeFrom.yNodePosition + 90.dp.value
            )

            val offsetToNode = Offset(
                edge.nodeTo.xNodePosition + 90.dp.value,
                edge.nodeTo.yNodePosition + 90.dp.value
            )
            DrawSingleEdge(start = offsetFromNode, end = offsetToNode, color = Color.Black)
        }
    }
}