package com.example.graphalgorithms.feature_algoritms.presentation.screen_basic_algorithms

import com.example.graphalgorithms.feature_algoritms.presentation.UndirectedGraphProvider
import com.example.graphalgorithms.feature_node.data.repository.FakeRepositoryForTesting
import com.example.graphalgorithms.feature_node.domain.entitiy.Edge
import com.example.graphalgorithms.feature_node.domain.entitiy.Node
import com.example.graphalgorithms.feature_node.domain.use_case.*
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.AddEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.DeleteEdgeUseCase
import com.example.graphalgorithms.feature_node.domain.use_case.EdgeUseCases.GetEdges
import com.example.graphalgorithms.feature_node.domain.use_case.NodeUseCases.AddNodeUseCase
import com.example.graphalgorithms.feature_node.presentation.MainCoroutineRule
import com.example.graphalgorithms.feature_node.presentation.NodeFeatureViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BasicAlgorithmsViewModelTest{
    private lateinit var viewModel: BasicAlgorithmsViewModel
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
            UndirectedGraph(FakeRepositoryForTesting(), FakeRepositoryForTesting())
        )
    }

    @Test
    fun `this graph has cycle, so hasGraphCycle should return true`() = runBlocking{
        val a = Node("a")
        val b = Node("b")
        val c = Node("c")
        val d = Node("d")
        val e = Node("e")
        val f = Node("f")

        val df = Edge(d,f,0f)
        val de = Edge(d,e,0f)
        val da = Edge(d,a,0f)
        val dc = Edge(d,c,0f)
        val db = Edge(d,b,0f)
        val ab = Edge(a,b,0f)
        val bc = Edge(b,c,0f)

        useCases.addNodeUseCase(a)
        useCases.addNodeUseCase(b)
        useCases.addNodeUseCase(c)
        useCases.addNodeUseCase(d)
        useCases.addNodeUseCase(e)
        useCases.addNodeUseCase(f)

        useCases.addEdgeUseCase(df)
        useCases.addEdgeUseCase(de)
        useCases.addEdgeUseCase(da)
        useCases.addEdgeUseCase(dc)
        useCases.addEdgeUseCase(db)
        useCases.addEdgeUseCase(ab)
        useCases.addEdgeUseCase(bc)

        viewModel = BasicAlgorithmsViewModel(UndirectedGraphProvider(useCases))
        assertThat(viewModel.hasGraphCycle.value).isEqualTo("true")

    }
}