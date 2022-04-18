package com.example.graphalgorithms.feature_node.presentation.screen_run_algorithm.screen_dijkstra.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class DijkstraUiEvent{
    data class EmitAdjacencyTable(val adjacencyTable:AdjacencyTable):DijkstraUiEvent()
    data class EmitVisitedNode(val node: Node): DijkstraUiEvent()
    data class EmitVisitedEdge(val edge: Edge): DijkstraUiEvent()
    object EmitAlgorithmEnd:DijkstraUiEvent()
}
