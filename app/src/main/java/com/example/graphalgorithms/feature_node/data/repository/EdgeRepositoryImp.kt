package com.example.graphalgorithms.feature_node.data.repository


import com.example.graphalgorithms.feature_node.data.source.EdgeDaoRepository
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository

class EdgeRepositoryImp(private val daoInterface: EdgeDaoRepository): EdgeRepository {
    override suspend fun add(edge: EdgeInDatabase) {
        daoInterface.add(edge)
    }

    override suspend fun delete(edge: EdgeInDatabase) {
        daoInterface.delete(edge)
    }

    override suspend fun getEdges(): List<EdgeInDatabase> {
        return daoInterface.getEdges()
    }
}