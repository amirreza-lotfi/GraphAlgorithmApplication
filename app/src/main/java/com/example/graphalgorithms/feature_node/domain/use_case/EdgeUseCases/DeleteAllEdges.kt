package com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases

import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class DeleteAllEdges(
    private val repo:EdgeRepository
) {
    suspend operator fun invoke() {
        repo.deleteAllEdges()
    }
}