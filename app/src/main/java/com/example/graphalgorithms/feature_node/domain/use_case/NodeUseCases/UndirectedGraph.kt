package com.example.graphalgorithms.feature_node.domain.use_case

import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.EdgeWithLabels
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.repository.EdgeRepository
import com.example.graphalgorithms.feature_node.domain.repository.NodeRepository
import com.example.graphalgorithms.feature_node.presentation.GraphScreenViewModel

class UndirectedGraph(val nodeRepository: NodeRepository, val edgeRepository: EdgeRepository) {

    suspend operator fun invoke():List<Node>{
        val nodeList = mutableListOf<Node>()

        nodeRepository.getNodesFromDatabase().onEach {
            nodeList.add(it)
        }
        edgeRepository.getEdges().onEach {
            val edge = EdgeWithLabels.getEdge(it, nodeList)

            val fromNode = GraphScreenViewModel.findNodeByLabel(edge.nodeFrom.label, nodeList)
            val toNode = GraphScreenViewModel.findNodeByLabel(edge.nodeTo.label, nodeList)

            fromNode.edges.add(edge)
            toNode.edges.add(
                Edge(
                toNode,
                fromNode,
                edge.weight,
                edge.id
                )
            )
        }
        return nodeList
    }
}