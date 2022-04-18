package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_prim

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class PrimUiEvent{
    data class EmitVisitedNode(val node: Node):PrimUiEvent()
    data class EmitVisitedEdge(val edge: Edge):PrimUiEvent()
    object OnAlgorithmEnded:PrimUiEvent()
}
