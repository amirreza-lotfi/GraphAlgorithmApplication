package com.example.graphalgorithms.feature_node.domain.use_case

import com.example.graphalgorithms.feature_node.data.repository.FakeRepositoryForTesting
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.AddEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteAllEdges
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.GetEdges
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.AddNodeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.DeleteAllNodes

data class UseCases(
    val addNodeUseCase: AddNodeUseCase,
    val deleteNodeUseCase: DeleteNodeUseCase,
    val getNodesUseCase: GetNodesUseCase,
    val getNodeUseCase: GetNodeUseCase,
    val addEdgeUseCase: AddEdgeUseCase,
    val deleteEdgeUseCase: DeleteEdgeUseCase,
    val getEdges: GetEdges,
    val undirectedGraph: UndirectedGraph,
    val deleteAllEdges: DeleteAllEdges,
    val deleteAllNodes: DeleteAllNodes
)