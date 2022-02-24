package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_dfs_traversal.util

import com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms.screen_bfs_traversal.util.BfsUiEvent
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class DfsUiEvent{
    object OnAlgorithmEnds:DfsUiEvent()
    data class DrawNodeOnScreen(val node: Node): DfsUiEvent()
    data class DrawEdgeOnScreen(val edge: Edge): DfsUiEvent()
}
