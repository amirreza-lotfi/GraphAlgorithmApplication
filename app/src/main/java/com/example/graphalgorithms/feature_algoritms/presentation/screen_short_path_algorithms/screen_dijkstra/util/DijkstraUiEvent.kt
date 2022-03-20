package com.example.graphalgorithms.feature_algoritms.presentation.screen_short_path_algorithms.screen_dijkstra.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class DijkstraUiEvent{
    data class EmitVisibilityArray(val visibilityArray:BooleanArray):DijkstraUiEvent()
    data class EmitDistanceArray(val distanceArray: FloatArray):DijkstraUiEvent()
    data class EmitLastNode(val lastNodeArray:ArrayList<String>):DijkstraUiEvent()
    data class EmitVisitedNode(val node: Node):DijkstraUiEvent()
    data class EmitVisitedEdge(val edge: Edge):DijkstraUiEvent()
}
