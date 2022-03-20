package com.example.graphalgorithms.feature_node.data.repository


import com.example.graphalgorithms.feature_node.data.source.EdgeDaoRepository
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class EdgeRepositoryImp(private val daoInterface: EdgeDaoRepository): EdgeRepository {
    override suspend fun add(edge: EdgeWithLabels) {
        daoInterface.add(edge)
    }

    override suspend fun delete(edge: EdgeWithLabels) {
        daoInterface.delete(edge)
    }

    override suspend fun getEdges(): List<EdgeWithLabels> {
        return daoInterface.getEdges()
    }

    override suspend fun deleteAllEdges() {
        daoInterface.deleteAllEdges()
    }
}