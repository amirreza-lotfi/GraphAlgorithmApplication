package com.example.graphalgorithms.feature_node.domain.repository

import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase

interface EdgeRepository {

    suspend fun add(edge: EdgeInDatabase)

    suspend fun delete(edge: EdgeInDatabase)

    suspend fun getEdges():List<EdgeInDatabase>
}