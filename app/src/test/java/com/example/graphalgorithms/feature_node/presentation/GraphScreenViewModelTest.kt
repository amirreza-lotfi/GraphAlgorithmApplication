package com.example.graphalgorithms.feature_node.presentation

import com.example.graphalgorithms.feature_node.data.repository.FakeRepositoryForTesting
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.*
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.AddEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.GetEdges
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.AddNodeUseCase
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class GraphScreenViewModelTest{
    private lateinit var screenViewModel: GraphScreenViewModel
    private lateinit var useCases: UseCases

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp(){
        useCases = UseCases(
            AddNodeUseCase(FakeRepositoryForTesting()),
            DeleteNodeUseCase(FakeRepositoryForTesting()),
            GetNodesUseCase(FakeRepositoryForTesting()),
            GetNodeUseCase(FakeRepositoryForTesting()),
            AddEdgeUseCase(FakeRepositoryForTesting()),
            DeleteEdgeUseCase(FakeRepositoryForTesting()),
            GetEdges(FakeRepositoryForTesting()),
            UndirectedGraph(FakeRepositoryForTesting(),FakeRepositoryForTesting()),

        )
        screenViewModel = GraphScreenViewModel(useCases)
    }

    @Test
    fun `Two nodes should never be selected at the same time`() = runBlockingTest{
        useCases.addNodeUseCase(Node("a"))
        useCases.addNodeUseCase(Node("b"))
        useCases.addNodeUseCase(Node("c"))

        screenViewModel = GraphScreenViewModel(useCases)

        val first = screenViewModel.nodeList[0]
        val second = screenViewModel.nodeList[1]

        screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))
        screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(second))

        var isOtherNodeExceptSecondNodeUnSelected = true
        for(node:Node in screenViewModel.nodeList){
            if(node.label!= second.label && node.isNodeSelected){
                isOtherNodeExceptSecondNodeUnSelected = false
                break
            }
        }
        assertThat(isOtherNodeExceptSecondNodeUnSelected).isTrue()
    }

    @Test
    fun `if select a node, then select it again, the all node should be unselected`() = runBlockingTest{
        useCases.addNodeUseCase(Node("a"))
        useCases.addNodeUseCase(Node("b"))
        useCases.addNodeUseCase(Node("c"))

        screenViewModel = GraphScreenViewModel(useCases)

        val first = screenViewModel.nodeList[0]

        screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))
        screenViewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))

        var isAllNodesUnSelected = true
        for(node:Node in screenViewModel.nodeList){
            if(node.isNodeSelected){
                isAllNodesUnSelected = false
                break
            }
        }
        assertThat(isAllNodesUnSelected).isTrue()
    }

}