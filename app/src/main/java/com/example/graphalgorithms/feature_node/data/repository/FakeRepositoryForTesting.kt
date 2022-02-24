package com.example.graphalgorithms.feature_node.data.repository

import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeInDatabase
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository

class FakeRepositoryForTesting: EdgeRepository,NodeRepository {

    private val edgeList = mutableListOf<EdgeInDatabase>()
    private val nodeList = mutableListOf<Node>()

    override suspend fun add(edge: EdgeInDatabase) {
        edgeList.add(edge)
    }

    override suspend fun delete(edge: EdgeInDatabase) {
        edgeList.remove(edge)
    }

    override suspend fun getEdges(): List<EdgeInDatabase> {
        return edgeList
    }

    override suspend fun add(node: Node) {
        nodeList.add(node)
    }

    override suspend fun delete(node: Node) {
        nodeList.remove(node)
    }

    override suspend fun getNodesFromDatabase(): List<Node> {
        return nodeList
    }

    override suspend fun getNodeFromDatabase(label: String): Node {
        for(node:Node in nodeList){
            if(node.label == label)
                return node
        }
        return Node("")
    }
}