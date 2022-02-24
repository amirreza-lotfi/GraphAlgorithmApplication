package com.example.graphalgorithms.feature_node.presentation.screen_graph.util


import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node

data class AddEditScreenEntity(
    val newNode:Node = Node(""),
    val edges: MutableList<Edge> = mutableListOf(),
    val weightOfEdge:String = "",
    var titleOfAddEditScreen:String ="Add Node"
)
