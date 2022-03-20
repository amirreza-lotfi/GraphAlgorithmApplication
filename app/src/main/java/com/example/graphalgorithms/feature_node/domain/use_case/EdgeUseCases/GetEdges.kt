package com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class GetEdges(
    private val repository: EdgeRepository
) {
    suspend operator fun invoke():List<EdgeWithLabels>{
        return repository.getEdges()
    }
    suspend fun getEdges(nodeList: List<Node>):List<Edge>{
        val edges = mutableListOf<Edge>()

        repository.getEdges()
            .onEach {
                edges.add(EdgeWithLabels.getEdge(it,nodeList))
            }
        return edges
    }
}