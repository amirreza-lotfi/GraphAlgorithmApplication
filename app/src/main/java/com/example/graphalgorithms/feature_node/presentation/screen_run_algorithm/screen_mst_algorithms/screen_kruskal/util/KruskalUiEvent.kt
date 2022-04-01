package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_mst_algorithms.screen_kruskal.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class KruskalUiEvent{
    data class EmitVisitedEdge(val edge: Edge):KruskalUiEvent()
    data class EmitVisitedNode(val node:Node):KruskalUiEvent()
    object OnAlgorithmEnd:KruskalUiEvent()
}
