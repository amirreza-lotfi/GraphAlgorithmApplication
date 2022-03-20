package com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases

import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository

class DeleteAllNodes (
    private val repo:NodeRepository
){
    suspend operator fun invoke(){
        repo.deleteAllNodes()
    }
}