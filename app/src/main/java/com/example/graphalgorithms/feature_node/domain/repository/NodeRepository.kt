package com.example.graphalgorithms.feature_node.domain.repository

import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import kotlinx.coroutines.flow.Flow

interface NodeRepository{

    suspend fun add(node: Node)

    suspend fun delete(node: Node)

    suspend fun getNodesFromDatabase(): List<Node>

    suspend fun getNodeFromDatabase(label:String):Node

    suspend fun deleteAllNodes()
}