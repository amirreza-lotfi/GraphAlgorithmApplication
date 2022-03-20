package com.example.graphalgorithms.feature_node.domain.repository

import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels

interface EdgeRepository {

    suspend fun add(edge: EdgeWithLabels)

    suspend fun delete(edge: EdgeWithLabels)

    suspend fun getEdges():List<EdgeWithLabels>

    suspend fun deleteAllEdges()
}