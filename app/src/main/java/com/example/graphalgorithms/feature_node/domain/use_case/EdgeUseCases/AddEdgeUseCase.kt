package com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class AddEdgeUseCase(
    private val repository: EdgeRepository
) {
    suspend operator fun invoke(edge: Edge){
        val edgeDBEntity = Edge.getEdgeDbEntity(edge)
        repository.add(edgeDBEntity)
    }
}