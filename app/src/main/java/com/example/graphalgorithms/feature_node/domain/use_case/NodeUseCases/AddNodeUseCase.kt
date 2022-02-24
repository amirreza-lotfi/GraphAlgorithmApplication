package com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases;

import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository

class AddNodeUseCase(
    private val repository:NodeRepository
) {
    suspend operator fun invoke(node: Node){
        repository.add(node)
    }
}
