package com.example.graphalgorithms.feature_node.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.graphalgorithms.feature_node.data.repository.FakeRepositoryForTesting
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.DeleteNodeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.AddEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.GetEdges
import com.example.graphalgorithms.feature_node.domain.use_case.GetNodeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.GetNodesUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.AddNodeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.UseCases
import com.example.graphalgorithms.feature_node.presentation.screen_graph.util.ScreenGraphEvent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class NodeFeatureViewModelTest{
    private lateinit var viewModel: NodeFeatureViewModel
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
            GetEdges(FakeRepositoryForTesting())
        )
        viewModel = NodeFeatureViewModel(useCases)
    }

    @Test
    fun `Two nodes should never be selected at the same time`() = runBlockingTest{
        useCases.addNodeUseCase(Node("a"))
        useCases.addNodeUseCase(Node("b"))
        useCases.addNodeUseCase(Node("c"))

        viewModel = NodeFeatureViewModel(useCases)

        val first = viewModel.nodeList[0]
        val second = viewModel.nodeList[1]

        viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))
        viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(second))

        var isOtherNodeExceptSecondNodeUnSelected = true
        for(node:Node in viewModel.nodeList){
            if(node.uniqueId!= second.uniqueId && node.isNodeSelected){
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

        viewModel = NodeFeatureViewModel(useCases)

        val first = viewModel.nodeList[0]

        viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))
        viewModel.onScreenGraphEvent(ScreenGraphEvent.OnNodeClicked(first))

        var isAllNodesUnSelected = true
        for(node:Node in viewModel.nodeList){
            if(node.isNodeSelected){
                isAllNodesUnSelected = false
                break
            }
        }
        assertThat(isAllNodesUnSelected).isTrue()
    }

}