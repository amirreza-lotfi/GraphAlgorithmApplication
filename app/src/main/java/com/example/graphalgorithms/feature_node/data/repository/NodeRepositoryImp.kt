package com.example.graphalgorithms.feature_node.data.repository

import com.example.graphalgorithms.feature_node.data.source.NodeDaoInterface
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository

class NodeRepositoryImp(private val daoInterface: NodeDaoInterface):NodeRepository {

    override suspend fun add(node: Node) {
        daoInterface.add(node)
    }

    override suspend fun delete(node: Node) {
        daoInterface.delete(node)
    }

    override suspend fun getNodesFromDatabase(): List<Node> {
        return daoInterface.getNodesFromDatabase()
    }

    override suspend fun getNodeFromDatabase(label: String): Node {
        return daoInterface.getNodeFromDatabase(label)
    }
}