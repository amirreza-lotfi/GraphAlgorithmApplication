package com.example.graphalgorithms.feature_node.presentation.screen_graph.util

import com.example.graphalgorithms.feature_node.domain.entitiy.Node

sealed class ScreenGraphEvent{
    data class OnNodeClicked(val node: Node):ScreenGraphEvent()
    data class NodePositionChanged(val node: Node, val x:Float, val y:Float):ScreenGraphEvent()
    object OnNavigateToEditScreen:ScreenGraphEvent()
    object OnNavigateToAddScreen:ScreenGraphEvent()
    data class SetRunAlgorithmButtonVisibility(val visibility:Boolean):ScreenGraphEvent()
    object DeleteSelectedNode:ScreenGraphEvent()
}
