package com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases

import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class GetEdges(
    private val repository: EdgeRepository
) {
    suspend operator fun invoke():List<EdgeInDatabase>{
        return repository.getEdges()
    }
}