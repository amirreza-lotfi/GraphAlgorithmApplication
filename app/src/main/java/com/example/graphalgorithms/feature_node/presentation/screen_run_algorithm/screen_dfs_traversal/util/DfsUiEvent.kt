package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dfs_traversal.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class DfsUiEvent{
    object OnAlgorithmEnds: DfsUiEvent()
    data class DrawNodeOnScreen(val node: Node): DfsUiEvent()
    data class DrawEdgeOnScreen(val edge: Edge): DfsUiEvent()
}
