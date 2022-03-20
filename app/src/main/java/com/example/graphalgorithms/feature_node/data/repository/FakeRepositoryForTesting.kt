package com.example.graphalgorithms.feature_node.data.repository

import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository

class FakeRepositoryForTesting: EdgeRepository,NodeRepository {

    private val edgeList = mutableListOf<EdgeWithLabels>()
    private val nodeList = mutableListOf<Node>()

    override suspend fun add(edge: EdgeWithLabels) {
        edgeList.add(edge)
    }

    override suspend fun delete(edge: EdgeWithLabels) {
        edgeList.remove(edge)
    }

    override suspend fun getEdges(): List<EdgeWithLabels> {
        return edgeList
    }

    override suspend fun deleteAllEdges() {
        edgeList.clear()
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

    override suspend fun deleteAllNodes() {
        nodeList.clear()
    }
}