package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.graphalgorithms.feature_node.domain.entitiy.Node


@Composable
fun DrawVisitedNodeWithRedColor(visitedNodes:List<Node>) {
    for (node: Node in visitedNodes) {
        DrawSingleNode(node = node, color = Color.Red)
    }
}