package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.presentation.ui.theme.red


@Composable
fun DrawVisitedNodeWithRedColor(visitedNodes:List<Node>) {
    for (node: Node in visitedNodes) {
        DrawSingleNode(node = node, color = red)
    }
}