package com.example.graphalgorithms.feature_node.domain.entitiy;

import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel.Companion.randomEdgeId


data class Edge(
    val nodeFrom:Node,
    val nodeTo:Node,
    var weight:Float = 0f,
    var id:Int = randomEdgeId()
){
    companion object{
        fun getEdgeDbEntity(edge: Edge):EdgeWithLabels{
            return EdgeWithLabels(
                edge.nodeFrom.label,
                edge.nodeTo.label,
                edge.weight,
                edge.id
            )
        }
    }
}
