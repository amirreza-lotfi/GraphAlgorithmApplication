package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_bfs_traversal.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class BfsUiEvent{
    data class DrawNodeOnScreen(val node: Node): BfsUiEvent()
    data class DrawEdgeOnScreen(val edge: Edge): BfsUiEvent()
    object BFSAlgorithmsEnds: BfsUiEvent()
}
