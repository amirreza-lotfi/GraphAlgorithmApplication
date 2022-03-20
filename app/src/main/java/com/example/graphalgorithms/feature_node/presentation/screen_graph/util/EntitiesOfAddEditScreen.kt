package com.example.graphalgorithms.feature_node.presentation.screen_graph.util


import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

data class EntitiesOfAddEditScreen(
    var nodeLabel:String = "",
    val edges: MutableList<EdgeWithLabels> = mutableListOf(),
    val weightOfEdge:String = "",
    var titleOfAddEditScreen:String ="Add Node"
)
